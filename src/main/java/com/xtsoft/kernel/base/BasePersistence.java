package com.xtsoft.kernel.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.xtsoft.kernel.query.ConditionFilter;

public class BasePersistence<T extends BaseEntity> extends SqlSessionDaoSupport {
	@Autowired
	public void setSqlSessionFactory(org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public void insert(String statement, T model) {
		getSqlSession().insert(statement, model);
	}

	public void update(String statement, T model) {
		getSqlSession().update(statement, model);
	}

	public void remove(String statement, T model) {
		getSqlSession().delete(statement, model);
	}

	public List selectList(String statement) {
		return selectList(statement, null);
	}

	public List selectList(String statement, Object parameter) {
		return getSqlSession().selectList(statement, parameter);
	}

	public List selectPageList(String statement, Object parameter, int start, int limit) {
		return getSqlSession().selectList(statement, parameter, new RowBounds(start, limit));
	}

	public Object selectOne(String statement, Object parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	public void insertEntity(T model) {
		getSqlSession().insert(model.getClass().getSimpleName() + BaseEntity.INSERTENTITY, model);
	}

	public void updatetEntity(T model) {
		getSqlSession().update(model.getClass().getSimpleName() + BaseEntity.UPDATEENTITY, model);
	}

	public void deleteEntity(T model) {
		getSqlSession().delete(model.getClass().getSimpleName() + BaseEntity.DELETEENTITY, model);
	}

	public Object findByPrimaryKey(Object model) {
		return getSqlSession().selectOne(model.getClass().getSimpleName() + BaseEntity.FINDPRIMARYKEY, model);
	}

	public long countWithDynamicQuery(String statement, List<ConditionFilter> list) {
		Map<String, Object> conditionFilter = new HashMap<String, Object>();
		if (list != null && list.size() > 0) {
			for (ConditionFilter filter : list) {
				conditionFilter.put(filter.getProperty(), filter.getValue());
			}
		}
		return getSqlSession().selectOne(statement, conditionFilter);
	}

	public List findWithDynamicQuery(String statement, List<ConditionFilter> list) {
		Map<String, Object> conditionFilter = new HashMap<String, Object>();
		if (list != null && list.size() > 0) {
			for (ConditionFilter filter : list) {
				conditionFilter.put(filter.getProperty(), filter.getValue());
			}
		}
		return getSqlSession().selectList(statement, conditionFilter);
	}

	public List findWithDynamicQuery(String statement, List<ConditionFilter> list, int start, int limit) {
		Map<String, Object> conditionFilter = new HashMap<String, Object>();
		if (list != null && list.size() > 0) {
			for (ConditionFilter filter : list) {
				conditionFilter.put(filter.getProperty(), filter.getValue());
			}
		}
		conditionFilter.put("startRow", start);
		conditionFilter.put("endRow", start + limit);
		return getSqlSession().selectList(statement, conditionFilter);
	}

}
