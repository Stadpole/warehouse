package com.bupt.common.base;

import com.bupt.common.utils.ReflectUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unchecked")
@Repository(value="simpleHibernateDaoImpl")
public class BasePageService<T,PK extends Serializable>  {

   private static final Pattern FROM_PATTERN  = Pattern.compile("[Ff][Rr][Oo][Mm]");
   private static final String  ORDER_PATTERN = "^(.*)[Oo][Rr][Dd][Ee][Rr]\\s*?[Bb][Yy](.*)$";


   @PersistenceContext
   protected EntityManager entityManager;

   protected Class<T>           entityClass;

   /**
    * 获得泛型参数的实际类型的class对象
    * eg.
    * SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User,
    * Long>(sessionFactory, User.class);
    */
   public BasePageService() {
       this.entityClass = ReflectUtils.getSuperClassGenricType(getClass());
   }


   /**
    * 获取sessionFactory.
    */
   public EntityManager getEntityManager() {
       return entityManager;
   }

   /**
    * 用注解@Autowired自动注入spring bean工厂中的sessionFactory
    */





   /**
    * 根据hql和参数数组查找数据
    *
    * @param values
    */
   protected <X> List<X> find(final String hql, final boolean isHql, final Object... values) {
       return createQuery(hql, isHql, values).getResultList();
   }

   /**
    * 根据hql和参数map查找数据
    *
    * @param values
    */
   protected <X> List<X> find(final String hql, final boolean isHql, final Map<String, ?> values) {
       return createQuery(hql, isHql, values).getResultList();
   }

   /**
    * 根据hql和参数数组查找唯一数据.
    *
    * @param values
    */
   protected <X> X findUnique(final String hql, final boolean isHql, final Object... values) {
       return (X) createQuery(hql, isHql, values).getSingleResult();
   }

   /**
    * 根据hql和参数map查找唯一数据
    *
    * @param values
    */
   protected <X> X findUnique(final String hql, final boolean isHql, final Map<String, ?> values) {
       return (X) createQuery(hql, isHql, values).getSingleResult();
   }

   /**
    * 批量更新数据库数据.
    *
    * @param values
    * @return
    */
   protected int batchExecute(final String hql, final boolean isHql, final Object... values) {
       return createQuery(hql, isHql, values).executeUpdate();
   }

   /**
    * 批量更新数据库数据..
    *
    * @param values
    * @return
    */
   protected int batchExecute(final String hql, final boolean isHql, final Map<String, ?> values) {
       return createQuery(hql, isHql, values).executeUpdate();
   }




   protected Query createQuery(final String queryString, final boolean isHql, final Object... values) {
       Assert.hasText(queryString, "queryString不能为空");
       Query query = isHql ? getEntityManager().createQuery(queryString) : getEntityManager().createNativeQuery(queryString,entityClass);
       if (values != null) {
           for (int i = 0; i < values.length; i++) {
               query.setParameter(i, values[i]);
           }
       }
       return query;
   }

   protected Query createQuery(final String queryString, final boolean isHql, final Map<String, ?> values) {
       Assert.hasText(queryString, "queryString不能为空");
       Query query = isHql ? getEntityManager().createQuery(queryString) : getEntityManager().createNativeQuery(queryString,entityClass);
       fillParams(query, values);
       return query;
   }
   protected Query createHqlQuery(final String hql, final Map<String, ?> values) {
       return createQuery(hql, true, values);
   }

   protected SQLQuery createSQLQuery(final String sql, final Object... values) {
       Assert.hasText(sql, "sql不能为空");
       Query query = getEntityManager().createNativeQuery(sql,entityClass);
       if (values != null) {
           for (int i = 0; i < values.length; i++) {
               query.setParameter(i, values[i]);
           }
       }
       return (SQLQuery)query;
   }

   /**
    *
    * @param sql
    * @param values
    * @return
    */
   protected SQLQuery createSQLQuery(final String sql, final Map<String, ?> values) {
       Assert.hasText(sql, "sql不能为空");
       Query query =getEntityManager().createNativeQuery(sql,entityClass);
       fillParams(query, values);
       return (SQLQuery)query;
   }



   /**
    * 根据hql查询出分页结果
    * eg: xxxDao.<Object> pageByHsql(hql, page, params, true)
    *
    * @author ycliu
    * @param hql hql
    * @return
    */
   protected final <X> void pageByHql(final String hql, final PageEntity<X> page, final Map<String, Object> params) {
       pageByHql(hql, page, params, true);
   }

   /**
    * eg: xxxDao.<Object> pageByHsql(hql, page, params, true)
    *
    * @author ycliu
    * @param hql
    * @param page
    * @param params
    * @param recount
    * @return
    */
   protected final <X> void pageByHql(final String hql, final PageEntity<X> page, final Map<String, Object> params,
           boolean recount) {
       Query countQuery = this.createQuery(generateCountHql(hql), true, params);
       Query query = this.createQuery(hql, true, params);

       doPage(page, countQuery, query, recount);
   }

   /**
    * eg: xxxDao.<X> pageByHsql(hql, page, params, true)
    *
    * @author ycliu
    * @param hql
    * @param page
    * @param params
    * @return
    */
   protected final <X> void pageByHqlWithOrder(final String hql, final PageEntity<X> page,
           final Map<String, Object> params) {
       Query countQuery = this.createQuery(generateCountHqlWithOrder(hql), true, params);
       Query query = this.createQuery(hql, true, params);

       doPage(page, countQuery, query, true);
   }



   /**
    *
    * @author ycliu
    * @param hql
    * @param page
    * @param qe
    * @param params
    * @param recount
    * @return
    */
   protected final <X> void pageBySql(final String hql, final PageEntity<X> page, QueryExtension<X> qe,
           final Map<String, Object> params, boolean recount) {
       Query countQuery = this.createQuery(generateCountHql(hql), false, params);
       Query query =  this.createQuery(hql, false, params);
       qe.doExtend((SQLQuery) query);

       doPage(page, countQuery, query, recount);
   }
   protected final <X> void pageBySql(final String hql, final PageEntity<X> page,
           final Map<String, Object> params, boolean recount) {
       Query countQuery = this.createQuery(generateCountHql(hql), false, params);
       Query query =  this.createQuery(hql, false, params);

       doPage(page, countQuery, query, recount);
   }

   /**
    * eg: xxxDao.<Object> pageByHql(hql, page, params)
    *
    * @author ycliu
    * @param hql hql
    * @param params
    * @param page page
    * @return
    */
   protected final <X> void pageByHql(final String hql, final PageEntity<X> page, final Object... params) {
       Query countQuery = this.createQuery(generateCountHql(hql), true, params);
       Query query = this.createQuery(hql, true, params);

       doPage(page, countQuery, query, true);
   }

   /**
    *
    * @author ycliu
    * @param sql
    * @param page
    * @param qe
    * @param params
    * @return
    */
   protected final <X> void pageBySql(final String sql, final PageEntity<X> page, QueryExtension<X> qe,
           final Object... params) {
       Query countQuery = this.createQuery(generateCountHql(sql), false, params);
       Query query =  this.createQuery(sql, false, params);

       qe.doExtend((SQLQuery) query);

       doPage(page, countQuery, query, true);
   }

//   /**
//    *
//    * @return String
//    */
//   protected String getEntityName() {
//       ClassMetadata meta = entityManager.get.getClassMetadata(entityClass);
//       return meta.getEntityName();
//   }

   /**
    *
    * @author ycliu
    * @param query query
    * @param params
    */
   private void fillParams(final Query query, final Map<String, ?> params) {
       if (null == query || null == params) {
           return;
       }
       for (Map.Entry<String, ?> entry : params.entrySet()) {
           if (null == entry.getValue() || StringUtils.isEmpty(entry.getValue().toString().trim())) {
               continue;
           }
           if (entry.getValue() instanceof Collection<?>) {
               query.setParameter(entry.getKey(), (Collection<?>) entry.getValue());
           } else if (entry.getValue() instanceof Object[]) {
               query.setParameter(entry.getKey(), (Object[]) entry.getValue());
           } else {
               query.setParameter(entry.getKey(), entry.getValue());
           }
       }
   }

   /**
    *
    * @author ycliu
    * @param querySql querySql
    * @return String
    */
   private String generateCountHql(final String querySql) {
       // 鍘绘帀order by
       final String newquerySql = querySql.replaceAll(ORDER_PATTERN, "$1");

       StringBuffer sb = new StringBuffer("select count(*) ");
       Matcher matcher = FROM_PATTERN.matcher(newquerySql);
       if (matcher.find()) {
           sb.append(newquerySql.substring(matcher.start()));
           return sb.toString();
       }

       return newquerySql;
   }

   /**
    *
    * @author guohuilin
    * @param querySql
    * @return
    */
   private String generateCountHqlWithOrder(final String querySql) {
       StringBuffer sb = new StringBuffer("select count(*) ");
       Matcher matcher = FROM_PATTERN.matcher(querySql);
       if (matcher.find()) {
           sb.append(querySql.substring(matcher.start()));
       }

       return sb.toString();
   }

   /**
    *
    * @author ycliu
    * @param page
    * @param countQuery
    * @param query
s    * @return
    */
   private <X> PageEntity<X> doPage(final PageEntity<X> page, Query countQuery, Query query, boolean recount) {
       if (recount) {
           Number count = (Number) countQuery.getSingleResult();
           page.setTotalResults(count == null ? 0 : count.longValue());
       }
       if (page.getCurrentPage() > 1){
           query.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
       }else {
           query.setFirstResult(page.getStart());
       }
       query.setMaxResults(page.getPageSize());

       List<X> results = query.getResultList();
       page.setCurrentPageLength(results.size());
       page.setTotalPages();
       page.setResults(results);

       return page;
   }
    protected void translate(List<T> list){

    }


}
