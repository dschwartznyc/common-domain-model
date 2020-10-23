package cdm.legalagreement.csa.processor;

import cdm.legalagreement.csa.CollateralTransferAgreementElections;
import cdm.legalagreement.csa.ElectiveAmountElection;
import cdm.legalagreement.csa.MinimumTransferAmountAmendment;
import cdm.observable.asset.Money;
import com.regnosys.rosetta.common.translation.Mapping;
import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.path.RosettaPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class MinimumTransferAmountAmendmentMappingProcessorTest {

	private static final String PARTY_A = "partyA";
	private static final String PARTY_B = "partyB";

	@Test
	void shouldMapMtaa() {
		// set up
		RosettaPath rosettaPath = RosettaPath.valueOf("LegalAgreement.csdInitialMargin2018EnglishLaw.minimumTransferAmountAmendment");
		List<Mapping> mappings = new ArrayList<>();
		mappings.add(new Mapping(Path.parse("answers.partyA.amendment_to_minimum_transfer_amount.partyA_amendment_to_minimum_transfer_amount"), "specify", null, null, "no destination", false, false));
		mappings.add(new Mapping(Path.parse("answers.partyA.amendment_to_minimum_transfer_amount.partyA_amount"), "1000", null, null, "no destination", false, false));
		mappings.add(new Mapping(Path.parse("answers.partyA.amendment_to_minimum_transfer_amount.partyA_currency"), "Japanese Yen", null, null, "no destination", false, false));
		mappings.add(new Mapping(Path.parse("answers.partyA.amendment_to_minimum_transfer_amount.partyB_amendment_to_minimum_transfer_amount"), "other", null, null, "no destination", false, false));
		mappings.add(new Mapping(Path.parse("answers.partyA.amendment_to_minimum_transfer_amount.partyB_specify"), "foo", null, null, "no destination", false, false));
		MappingContext context = new MappingContext(mappings, Collections.emptyMap());

		MinimumTransferAmountAmendment.MinimumTransferAmountAmendmentBuilder builder = MinimumTransferAmountAmendment.builder();
		CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder parent =
				mock(CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder.class);

		// test
		Path synonymPath = Path.parse("answers.partyA.amendment_to_minimum_transfer_amount");
		MinimumTransferAmountAmendmentMappingProcessor processor =
				new MinimumTransferAmountAmendmentMappingProcessor(rosettaPath,
						Collections.singletonList(synonymPath),
						context);
		processor.map(synonymPath, builder, parent);
		MinimumTransferAmountAmendment minimumTransferAmountAmendment = builder.build();

		// assert

		ElectiveAmountElection partyA = getPartyElection(minimumTransferAmountAmendment, PARTY_A);
		assertNull(partyA.getCustomElection());
		Money partyAAmount = partyA.getAmount();
		assertEquals(1000, partyAAmount.getAmount().intValue());
		assertEquals("JPY", partyAAmount.getCurrency().getValue());

		ElectiveAmountElection partyB = getPartyElection(minimumTransferAmountAmendment, PARTY_B);
		assertEquals("foo", partyB.getCustomElection());
		assertNull(partyB.getAmount());
		assertNull(partyB.getZeroAmount());
	}

	private ElectiveAmountElection getPartyElection(MinimumTransferAmountAmendment minimumTransferAmountAmendment, String party) {
		return minimumTransferAmountAmendment.getPartyElections().stream()
				.filter(e -> party.equals(e.getParty()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("No partyElection found for " + party));
	}
}