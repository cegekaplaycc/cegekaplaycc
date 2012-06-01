package models.stock;


import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.Assertions;
import org.junit.Test;

import play.test.UnitTest;

public class StockTest extends UnitTest{

	@Test
	public void add() {
		Stock stock = new Stock();
		Food supply = new Food();
		
		stock.add(supply);
		
		Assertions.assertThat(stock.items).hasSize(1);
		Assertions.assertThat(stock.items.iterator().next().supply).isEqualTo(supply);
		Assertions.assertThat(stock.items.iterator().next().amount).isEqualTo(1);
	}
	
	@Test
	public void addItemAlreadyInStock() {
		Food supply = new Food();
		Stock stock = new StockBuilder().withSupply(supply, 2).build();

		Assertions.assertThat(stock.items).hasSize(1);
		Assertions.assertThat(stock.items.iterator().next().amount).isEqualTo(2);
		
		stock.add(supply);
		
		Assertions.assertThat(stock.items).hasSize(1);
		Assertions.assertThat(stock.items.iterator().next().amount).isEqualTo(3);
	}

	@Test
	public void addItemWithAmount() {
		Food supply = new Food();
		Stock stock = new StockBuilder().withSupply(supply, 2).build();
		
		stock.add(supply, 12);
		
		Assertions.assertThat(stock.items).hasSize(1);
		Assertions.assertThat(stock.items.iterator().next().amount).isEqualTo(14);
	}

	@Test
	public void addItemWithAmountNotYetInList() {
		Stock stock = new StockBuilder().build();
		
		stock.add(new Food(), 7);
		
		Assertions.assertThat(stock.items).hasSize(1);
		Assertions.assertThat(stock.items.iterator().next().amount).isEqualTo(7);
	}

}
