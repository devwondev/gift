package com.gift.futurestrading.member.vo;

public class InformationOfConsumerAccountVo {
	private String fkConsumerAccountConsumer;
	private String accountConsumerName;
	private String accountConsumerAccountNo;
	private String accountConsumerBankName;
	private String accountConsumerSignUpDate;
	public String getFkConsumerAccountConsumer() {
		return fkConsumerAccountConsumer;
	}
	public void setFkConsumerAccountConsumer(String fkConsumerAccountConsumer) {
		System.out.println(fkConsumerAccountConsumer + " <---InformationOfConsumerAccountVo.setFkConsumerAccountConsumer() 호출");
		this.fkConsumerAccountConsumer = fkConsumerAccountConsumer;
	}
	public String getAccountConsumerName() {
		return accountConsumerName;
	}
	public void setAccountConsumerName(String accountConsumerName) {
		System.out.println(accountConsumerName + " <---InformationOfConsumerAccountVo.setAccountConsumerName() 호출");
		this.accountConsumerName = accountConsumerName;
	}
	public String getAccountConsumerAccountNo() {
		return accountConsumerAccountNo;
	}
	public void setAccountConsumerAccountNo(String accountConsumerAccountNo) {
		System.out.println(accountConsumerAccountNo + " <---InformationOfConsumerAccountVo.setAccountConsumerAccountNo() 호출");
		this.accountConsumerAccountNo = accountConsumerAccountNo;
	}
	public String getAccountConsumerBankName() {
		return accountConsumerBankName;
	}
	public void setAccountConsumerBankName(String accountConsumerBankName) {
		System.out.println(accountConsumerBankName + " <---InformationOfConsumerAccountVo.setAccountConsumerBankName() 호출");
		this.accountConsumerBankName = accountConsumerBankName;
	}
	public String getAccountConsumerSignUpDate() {
		return accountConsumerSignUpDate;
	}
	public void setAccountConsumerSignUpDate(String accountConsumerSignUpDate) {
		System.out.println(accountConsumerSignUpDate + " <---InformationOfConsumerAccountVo.setAccountConsumerSignUpDate() 호출");
		this.accountConsumerSignUpDate = accountConsumerSignUpDate;
	}
	
	@Override
	public String toString() {
		return "InformationOfConsumerAccountVo [fkConsumerAccountConsumer=" + fkConsumerAccountConsumer
				+ ", accountConsumerName=" + accountConsumerName + ", accountConsumerAccountNo="
				+ accountConsumerAccountNo + ", accountConsumerBankName=" + accountConsumerBankName
				+ ", accountConsumerSignUpDate=" + accountConsumerSignUpDate + "]";
	}	
	
}
