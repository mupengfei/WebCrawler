package com.webcrawler.taobao;

import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class TBreply {
	private int id;
	private String goodsId;
	private String username;
	private String goumai;
	private String reply;
	public boolean save() {
		String sql = "insert into tb_reply values (null,?,?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.goodsId, this.username, this.goumai,
				this.reply});
	}
	public TBreply(Map map) {
		super();
		this.id = (Integer)map.get("id");
		this.goodsId = (String)map.get("goods_id");
		this.username = (String)map.get("username");
		this.goumai = (String)map.get("goumai");
		this.reply = (String)map.get("reply");
	}
	public TBreply(String goodsId, String username, String goumai, String reply) {
		super();
		this.goodsId = goodsId;
		this.username = username;
		this.goumai = goumai;
		this.reply = reply;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGoumai() {
		return goumai;
	}
	public void setGoumai(String goumai) {
		this.goumai = goumai;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
