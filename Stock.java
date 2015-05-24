import java.util.*;


public class Stock {
	private String stockName;
	private String stockQuote;
	private String market;
	private String marketSector;
	private double eps;
	private double pe;
	private ArrayList<String> relatedKeyWords;
	private ArrayList<Double> ;
	
	public Stock(String stockName, String stockQuote, String marketSector, String market) {
		this.stockName = stockName;
		this.stockQuote = stockQuote;
		this.marketSector = market;
		this.marketSector = marketSector;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public String getStockName() {
		return this.stockName;
	}
	
	public void setStockQuote(String stockQuote) {
		this.stockQuote = stockQuote;
	}
	
	public String getStockQuote() {
		return this.stockQuote;
	}
	
	public void setMarketSector(String marketSector) {
		this.marketSector = marketSector;
	}
	
	public String getMarketSector() {
		return this.marketSector;
	}
	
	public void setEPS(double eps) {
		this.eps = eps;
	}
	
	public double getEPS() {
		return this.eps;
	}
}
