package com.colson.service.bean.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author bohan
 * <p>
 * Bean拷贝基础工厂，可直接在Service中Autowired MapperFacade使用拷贝功能
 */
@Component
public class MapperFacadeFactory implements FactoryBean<MapperFacade> {
	public MapperFacade getObject() throws Exception {
		DefaultMapperFactory builder = new DefaultMapperFactory.Builder()
				.mapNulls(false).build();

//		builder.classMap(DebtRepayDetailsEntity.class,DebitDetailBean .class).customize(new CustomMapper<DebtRepayDetailsEntity, DebitDetailBean>() {
//			@Override
//			public void mapAtoB(DebtRepayDetailsEntity debitEntity, DebitDetailBean debitBean, MappingContext context) {
//				if(!Strings.isNullOrEmpty(debitEntity.getFeeDetailJson())) {
//					debitBean.setFeeDetailList(LoanJsonUtils.getFeeModelList(debitEntity.getFeeDetailJson()));
//				}
//			}
//
//			@Override
//			public void mapBtoA(DebitDetailBean debitBean, DebtRepayDetailsEntity debitEntity, MappingContext context) {
//				if(debitBean.getFeeDetailList() != null) {
//					debitEntity.setFeeDetailJson(LoanJsonUtils.toFeeModelJson(debitBean.getFeeDetailList()));
//				}
//			}
//		}).byDefault().register();

		return builder.getMapperFacade();
	}

	public Class<?> getObjectType() {
		return MapperFacade.class;
	}

	public boolean isSingleton() {
		return true;
	}


}
