package com.xtsoft.kernel.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.xtsoft.kernel.annotation.SystemLogAnnotation;
import com.xtsoft.kernel.base.BaseEntity;
import com.xtsoft.kernel.query.ConditionFilter;
import com.xtsoft.kernel.response.AjaxJsonResult;
import com.xtsoft.kernel.sys.entity.LogEventEntity;
import com.xtsoft.kernel.sys.service.CounterEntityServiceUtil;
import com.xtsoft.kernel.sys.service.LogEventEntityServiceUtil;

@Aspect
@Component
public class SystemLogAspect {
	private static final Logger log = LoggerFactory.getLogger(SystemLogAspect.class);

	@Pointcut("execution(* com.xtsoft..*Action.*(..)) and !execution(* com.xtsoft..*Action.set*(..)) ")
	public void aspect() {
	}

	@Around("aspect()")
	public Object around(JoinPoint joinPoint) {
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		SystemLogAnnotation annotation = method.getAnnotation(SystemLogAnnotation.class);
		Object object = null;
		if (annotation != null) {
			long start = System.currentTimeMillis();
			LogEventEntity logEvent = null;
			try {
				object = ((ProceedingJoinPoint) joinPoint).proceed();
				logEvent = getLogEvent(joinPoint);
				logEvent.setDescription(annotation.description());
				long end = System.currentTimeMillis();
				logEvent.setExeTime((end - start));
				logEvent.setSucess(1);
				logEvent.setOperTime(new Date());
				logEvent.setId(CounterEntityServiceUtil.getService().getPersistence().increment(LogEventEntity.class.getName()) + "");
				if (object != null) {
					if (object instanceof AjaxJsonResult) {
						SimplePropertyPreFilter filter = new SimplePropertyPreFilter("result", "message");
						logEvent.setSucess(((AjaxJsonResult) object).getResult());
						// logEvent.setDescription(description);
						logEvent.setReturnObject(JSON.toJSONString(object, filter));
					}

				}
				LogEventEntityServiceUtil.getService().getPersistence().insertEntity(logEvent);

			} catch (Throwable e) {
				logEvent = getLogEvent(joinPoint);
				logEvent.setDescription(annotation.description());
				long end = System.currentTimeMillis();
				logEvent.setExeTime((end - start));
				logEvent.setOperTime(new Date());
				logEvent.setSucess(0);
				logEvent.setExcetption(e.getMessage());
				LogEventEntityServiceUtil.getService().getPersistence().insertEntity(logEvent);

			}
		} else {
			try {
				object = ((ProceedingJoinPoint) joinPoint).proceed();
			} catch (Throwable e) {

			}

		}
		return object;
	}

	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
		if (log.isInfoEnabled()) {
			log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
		}
	}

	public LogEventEntity getLogEvent(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		LogEventEntity l = new LogEventEntity();

		return l;
	}

	public String getControllerMethodDescription(Object[] arguments) throws Exception {
		StringBuffer rs = new StringBuffer();
		if (arguments == null) {
			return rs.toString();
		}
		String className = null;
		rs.append("[");
		for (Object info : arguments) {

			if (info == null) {
				if (!rs.toString().equals("")) {
					rs.append(",");
				}
				rs.append("{}");
			} else {
				className = info.getClass().getName();
				className = className.substring(className.lastIndexOf(".") + 1);
				if (info instanceof BaseEntity) {
					if (!rs.toString().equals("[")) {
						rs.append(",");
					}
					rs.append("{");
					rs.append(info.toString());
					rs.append("}");
				} else {
					if (info instanceof List) {
						if (!rs.toString().equals("")) {
							rs.append(",");
						}
						rs.append("{");
						for (Object r : (List) info) {
							int k = 0;
							if (info instanceof ConditionFilter) {
								if (k == 0) {
									rs.append(info.toString());
									k = k + 1;
								}
							}
						}
						rs.append("}");

					} else {
						if (!rs.toString().equals("")) {
							rs.append(",");
						}
						rs.append("{");
						rs.append(info.toString());
						rs.append("}");
					}
				}
			}
		}
		rs.append("]");
		return rs.toString();
	}

}
