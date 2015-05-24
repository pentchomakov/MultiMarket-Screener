import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Gatherer {
	private static int DELAY;
	private static int PERIOD;
	private static final int MYTHREADS = 30;
	static String [] quotes = {"MG.TO", "CP.TO", "NA.TO"};
	
	public static ArrayList<Market> markets = new ArrayList<Market>();
	
	public static void main(String [] args) {
		DELAY = 1000;
		PERIOD = 5000;
		stocksGathererRepeated(quotes, DELAY, PERIOD);
		
		/*addMarket("TSX", "Canada", 930, 1600);
		addStock("Magna International Inc.", "MG.TO", "industrial", "TSX");
		addStock("Alimentation Couche-Tard", "ATD/B.TO", "retail", "TSX");
		printStocksInMarket("TSX");
		printAllMarkets();*/
		
		/*addMarket("NASDAQ", "USA", 930, 1600);
		populateMarket("nasdaq.csv", "NASDAQ");
		addMarket("NYSE", "USA", 930, 1600);
		populateMarket("nyse.csv", "NYSE");
		addMarket("AMEX", "USA", 930, 1600);
		populateMarket("amex.csv", "AMEX");
		printStocksInMarket("NASDAQ");
		printStocksInMarket("NYSE");
		printStocksInMarket("AMEX");
		printAllMarkets();*/
		
		/*printMarketInfo("TSX");
		printStocksInMarket("S&P500");
		
		addStock("Magna International Inc.", "MG.TO", "industrial", "TSX");
		addStock("Alimentation Couche-Tard", "ATD/B.TO", "retail", "TSX");
		printStocksInMarket("TSX");
		*/
		
		//getStockCSVData("MG.TO");
	}
	
	public static void stocksGathererRepeated(String [] quotes, int delay, int period) {
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
				
				for(int i = 0; i < quotes.length; i++) {
					String quote = quotes[i];
					Runnable stock = new singleStockGatherer(quote);
					executor.execute(stock);
				}
				
				executor.shutdown();
				while(!executor.isTerminated()) {  }
				System.out.println("Finished all threads");
			}
		}, delay, period);
	}
	
	public static class singleStockGatherer implements Runnable {
		private final String quote;
		
		singleStockGatherer(String quote) {
			this.quote = quote;
		}
		
		@Override
		public void run() {
			try {
				URL oracle = new URL(constructURL(quote));
				URLConnection connection = oracle.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String currentLine = "";
				if((currentLine = br.readLine())!= null) {
					System.out.println(currentLine);
				}
				/*
				while((currentLine = br.readLine()) != null) {
					System.out.println(currentLine);
				}
				*/
				br.close();	
			}
			
			catch(IOException e) {
				System.out.println(e);
			}
		}
		
	}
	
	public static String constructURL(String quote) {
		quote = quote.toUpperCase();
		return "http://download.finance.yahoo.com/d/quotes.csv?s=%22%20+%20"+ quote +"%20+%20%22&f=nsl1op&e=.csv%22";
	}
	
	// Imports the stocks contained in a .csv file into a Market Object
	public static void populateMarket(String filePath, String market) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String currentLine = "";
			currentLine = br.readLine();
			int counter = 0;
			/*currentLine = br.readLine();
			String [] parts = currentLine.split(",");
			addStock(parts[1], parts[0], parts[2], market);*/
			while((currentLine = br.readLine()) != null) {
				String [] parts = currentLine.split(",");
				addStock(parts[1], parts[0], parts[5], market);
			}
		}
		
		catch(IOException e) {
			System.out.println(e);
		}
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
	public static void addMarket(String marketName, String marketLocation, int openingTime, int closingTime) {
		Market market = new Market(marketName, marketLocation, openingTime, closingTime);
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
			}
		}
	}
	
	// Prints all markets in the markets ArrayList
	public static void printAllMarkets() {
		for(int i = 0; i < markets.size(); i++) {
			System.out.println(markets.get(i).getMarketName());
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
					System.out.print(stocks.get(j).getStockQuote());
					System.out.print(", " + stocks.get(j).getStockName());
					System.out.println(", " + stocks.get(j).getMarketSector());
				}
				break;
			}
		}
	}
	
}
