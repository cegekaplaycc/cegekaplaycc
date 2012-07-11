package models.stock;

import litmus.unit.UnitTest;
import org.junit.Test;


public class StockTest extends UnitTest {

	@Test
	public void add() {
		Stock stock = new Stock();
		Food supply = Food.HAY;

		stock.add(supply);

		assertThat(stock.items).hasSize(1);
		assertThat(stock.items.iterator().next().supply).isEqualTo(supply);
		assertThat(stock.items.iterator().next().amount).isEqualTo(1);
	}

	@Test
	public void addItemAlreadyInStock() {
		Food supply = Food.HAY;
		Stock stock = new StockBuilder().withSupply(supply, 2).build();

		assertThat(stock.items).hasSize(1);
		assertThat(stock.items.iterator().next().amount).isEqualTo(2);

		stock.add(supply);

		assertThat(stock.items).hasSize(1);
		assertThat(stock.items.iterator().next().amount).isEqualTo(3);
	}

	@Test
	public void addItemWithAmount() {
		Food supply = Food.HAY;
		Stock stock = new StockBuilder().withSupply(supply, 2).build();

		stock.add(supply, 12);

		assertThat(stock.items).hasSize(1);
		assertThat(stock.items.iterator().next().amount).isEqualTo(14);
	}

	@Test
	public void addItemWithAmountNotYetInList() {
		Stock stock = new StockBuilder().build();

		stock.add(Food.HAY, 7);

		assertThat(stock.items).hasSize(1);
		assertThat(stock.items.iterator().next().amount).isEqualTo(7);
	}
	
	@Test
	public void getAmountForFood_NullStockGeeft0() {
		Stock stock = new StockBuilder()
			.withoutSupplies()
			.build();

		assertThat(stock.getAmountForFood(Food.CARROTS)).isZero();
	}

	@Test
	public void getAmountForFood_WelStockGeeftAmount() {
		Stock stock = new StockBuilder()
			.withSupply(Food.CARROTS, 5)
			.build();

		assertThat(stock.getAmountForFood(Food.CARROTS)).isEqualTo(5);
	}

}
