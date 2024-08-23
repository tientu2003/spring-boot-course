package rewards;

import common.money.MonetaryAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class RewardNetworkTests {

    private RewardNetwork rewardNetwork;

    @BeforeEach
    public void setUp() {
      ApplicationContext context = SpringApplication.run(TestInfrastructureConfig.class);
      this.rewardNetwork = context.getBean(RewardNetwork.class);
    }

    @Test
    void testRewardForDining(){
        Dining dining =  Dining.createDining("10.00","1234123412341234","1234567890");

        RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);

        assertNotNull(confirmation);
        assertNotNull(confirmation.getConfirmationNumber());

        AccountContribution accountContribution = confirmation.getAccountContribution();
        assertNotNull(accountContribution);

        assertEquals(accountContribution.getAccountNumber(),"123456789");

        assertEquals(accountContribution.getAmount(), MonetaryAmount.valueOf("0.80"));

        assertEquals(accountContribution.getDistribution("Annabelle").getAmount(), MonetaryAmount.valueOf("0.40"));

        assertEquals(accountContribution.getDistribution("Corgan").getAmount(), MonetaryAmount.valueOf("0.40"));

    }


}