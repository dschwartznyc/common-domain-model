package com.regnosys.granite.projector.isdacreate;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.legalagreement.common.GoverningLawEnum;
import cdm.legalagreement.common.LegalAgreementNameEnum;
import cdm.legalagreement.common.LegalAgreementPublisherEnum;
import cdm.legalagreement.csa.AmendmentEffectiveDateEnum;
import cdm.legalagreement.csa.SensitivitiesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class IsdaCreateEnumMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(IsdaCreateEnumMapper.class);

	String getGoverningLaw(GoverningLawEnum governingLawEnum) {
		switch(governingLawEnum) {
		case GBEN:
			return "English";
		case JP:
			return "Japanese";
		case USNY:
			return "New York";
		default:
			LOGGER.warn("Unknown enum value {}", governingLawEnum);
			return governingLawEnum.toString();
		}
	}

	String getAmendmentEffectiveDate(AmendmentEffectiveDateEnum amendmentEffectiveDateEnum) {
		switch(amendmentEffectiveDateEnum) {
		case ANNEX_DATE:
			return "annex";
		default:
			LOGGER.warn("Unknown enum value {}", amendmentEffectiveDateEnum);
			return amendmentEffectiveDateEnum.toString();
		}
	}

	String getDocumentType(LegalAgreementNameEnum legalAgreementNameEnum) {
		switch(legalAgreementNameEnum) {
		case CREDIT_SUPPORT_DEED:
			return "Credit Support Deed";
		case CREDIT_SUPPORT_ANNEX:
			return "Credit Support Annex";
		default:
			LOGGER.warn("Unknown enum value {}", legalAgreementNameEnum);
			return legalAgreementNameEnum.toString();
		}
	}

	String getPublisher(LegalAgreementPublisherEnum legalAgreementPublisherEnum) {
		switch(legalAgreementPublisherEnum) {
		case ISDA:
			return "ISDA";
		default:
			LOGGER.warn("Unknown enum value {}", legalAgreementPublisherEnum);
			return legalAgreementPublisherEnum.toString();
		}
	}

	String getSensitivities(SensitivitiesEnum sensitivitiesEnum) {
		switch(sensitivitiesEnum) {
		case ALTERNATIVE:
			return "alternative";
		case STANDARD:
			return "standard";
		default:
			LOGGER.warn("Unknown enum value {}", sensitivitiesEnum);
			return sensitivitiesEnum.toString();
		}
	}

	String getBusinessCenter(BusinessCenterEnum businessCenterEnum) {
		switch(businessCenterEnum) {
		case CNBE:
			return "Beijing, China";
		case DEFR:
			return "Frankfurt, Germany";
		case GBLO:
			return "London, United Kingdom";
		case NLAM:
			return "Amsterdam, Netherlands";
		case SGSI:
			return "Singapore, Singapore";
		case CATO:
			return "Toronto, Canada";
		default:
			LOGGER.warn("Unknown enum value {}", businessCenterEnum);
			return businessCenterEnum.toString();
		}
	}
}