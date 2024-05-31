package ru.gb.spring.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.spring.aop.service.BusinessService;
import ru.gb.spring.aop.service.HometaskService;
import ru.gb.spring.aop.service.SecondService;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {




		ConfigurableApplicationContext context =SpringApplication.run(SpringAopApplication.class, args);
		BusinessService bS = context.getBean(BusinessService.class);
		SecondService sS = context.getBean(SecondService.class);
		HometaskService hS = context.getBean(HometaskService.class);


		hS.doSmth(hS.returnSmth(56));

/*      пока закомментируем, чтобы была видна тольк домашка :
		System.out.println(bS.foo("khe-khe"));
		System.out.println(bS.foo(null));
		System.out.println(sS.bar("apchi"));
		System.out.println(sS.bar(null));*/

		context.close();
	}

}
