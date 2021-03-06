package cc.moondust.exception.resolver;

import cc.moondust.exception.BaseException;
import cc.moondust.exception.UnKnowException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 *
 * @author XuJijun
 */
@Aspect
@Component
public class ControllerInterceptor {
    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* cc.moondust.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object proceed = pjp.proceed();
            Object result = new ResponseEntity("success", proceed);
            return result;
        }catch (Exception e){
            if(e instanceof BaseException){
                throw e;
            }else {
                throw new  UnKnowException(500,e.getMessage());
            }
        }
    }

}