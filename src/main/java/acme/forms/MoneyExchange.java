package acme.forms;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyExchange {

	// Query attributes -------------------------------------------------------

	@NotNull
	@Valid
	protected Money		source;

	@NotBlank
	protected String	targetCurrency;

	// Response attributes ----------------------------------------------------

	@Setter(AccessLevel.NONE)
	@Valid
	protected Money		target;

	@Setter(AccessLevel.NONE)
	protected Date		date;

	// Ancillary methods ------------------------------------------------------
	
	public static MoneyExchange of(final Money source, final String targetCurrency) {
		final MoneyExchange result = new MoneyExchange();
		result.setSource(source);
		result.setTargetCurrency(targetCurrency);
		return result;
	}
	
	public static Money moneyOf(final Double amount, final String currency) {
		final Money result = new Money();
		result.setAmount(amount);
		result.setCurrency(currency);
		return result;
	}
	
	public MoneyExchange execute() {
		assert this.source != null;
		assert !StringHelper.isBlank(this.targetCurrency);

		RestTemplate api;
		ExchangeRate record;
		String sourceCurrency;
		Double sourceAmount, targetAmount, rate;

		try {
			api = new RestTemplate();

			sourceCurrency = this.source.getCurrency();
			sourceAmount = this.source.getAmount();

			record = api.getForObject( //
				"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
				ExchangeRate.class, //
				sourceCurrency, //
				this.targetCurrency //
			);

			assert record != null;
			rate = record.getRates().get(this.targetCurrency);
			targetAmount = rate * sourceAmount;

			this.target = new Money();
			this.target.setAmount(targetAmount);
			this.target.setCurrency(this.targetCurrency);

			this.date = record.getDate();
		} catch (final Throwable oops) {
			this.target = null;
			this.date = null;
		}

		return this;
	}
	
}
