import java.util.*;

public class Main {
	public static ArrayList<Market> markets = new ArrayList<Market>();
	
	public static void main(String [] args) {
		
		
		
		
		/*
		Market market = new Market("TSX", "Canada", 60, 930, 1600);
		markets.add(market);
		printMarketInfo("TSX");
		
		addStock("Magna International Inc.", "MG.TO", "industrial", "TSX");
		addStock("Alimentation Couche-Tard", "ATD/B.TO", "retail", "TSX");
		printStocksInMarket("TSX");
		*/
		getStockCSVData("MG.TO", "22-5-2014", "10-5-2015" );
	}
	
	public static void getStockCSVData(String quote, String startDate, String endDate) {
		String [] startParts = startDate.split("-");
		String [] endParts = endDate.split("-");
		String URL = constructURL(quote, Integer.parseInt(startParts[0]), Integer.parseInt(startParts[1]), Integer.parseInt(startParts[2]),
				Integer.parseInt(endParts[0]), Integer.parseInt(endParts[1]), Integer.parseInt(endParts[2]));
		
		return UrlUtils.getUrlString(URL);
	}
	
	public static String constructURL(String quote, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
		return "http://ichart.finance.yahoo.com/table/csv" + "?s=" + 
				quote + "&a=" + startMonth + "&b=" + startDay + "&c=" + startYear + "&d=" + endMonth + "&e=" + endDay + "&f=" + endYear +
				"&g=d&ignore=.csv";
	}
	
	// Creates a new stock and adds it to a given market (String)
	public static void addStock(String stockName, String stockQuote, String stockSector, String market) {
		Stock stock = new Stock(stockName, stockQuote, stockSector, market);
		market = market.toUpperCase();
		
		for(int i = 0; i < markets.size(); i++) {
			if(markets.get(i).getMarketName().equals(market)) {
				markets.get(i).getStockList().add(stock);
			}
		}
	}
	
	// Adds a new market to the markets ArrayList
	public static void addMarket(String marketName, String marketLocation, int size, int openingTime, int closingTime) {
		Market market = new Market(marketName, marketLocation, size, openingTime, closingTime);
		markets.add(market);
	}
	
	// Prints the global information about a given market (String)
	public static void printMarketInfo(String market) {
		if(market == null) {
			return;
		}
		
		market = market.toUpperCase();
		
		for(int i = 0; i < markets.size(); i++) {
			if(markets.get(i).getMarketName().equals(market)) {
				System.out.println(markets.get(i).getMarketName().toString());
				System.out.println(markets.get(i).getMarketLocation().toString());
				System.out.println(markets.get(i).getMarketSize());
			}
		}
	}
	
	// Prints the name of all the stocks in a given market (String)
	public static void printStocksInMarket(String market) {
		if(market == null) {
			return;
		}
		
		market = market.toUpperCase();
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		for(int i = 0; i < markets.size(); i++) {
			if(markets.get(i).getMarketName().equals(market)) {
				stocks = markets.get(i).getStockList();
				for(int j = 0; j < markets.get(i).getStockList().size(); j++) {
					System.out.println(stocks.get(j).getStockName());
				}
				break;
			}
		}
	}
	
}
