package com.example.weixin.popular.bean.card.qrcode.create;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 
 * @author Moyq5
 *
 */
public class ActionInfoMultipleCard {

	@JSONField(name = "card_list")
	List<ActionInfoMultipleCardItem> cardList;

	public List<ActionInfoMultipleCardItem> getCardList() {
		return cardList;
	}

	public void setCardList(List<ActionInfoMultipleCardItem> cardList) {
		this.cardList = cardList;
	}
}
