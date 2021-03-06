package com.rextuz.onlinechess.pieces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.rextuz.onlinechess.Board;
import com.rextuz.onlinechess.OnlineChess;

public class Piece implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int x, y;
	protected float size;
	protected Texture texture;
	protected String color;
	protected Board board;
	protected Sprite sprite;

	public Piece(int x, int y, String color, Board board) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.board = board;
		this.size = board.getSize() / 8;
	}

	public List<Available> getMoves() {
		List<Available> list = new ArrayList<Available>();
		return list;
	}

	public List<Available> getEnemyMoves() {
		List<Available> list = getMoves();
		List<Available> newList = new ArrayList<Available>();
		for (Available a : list)
			newList.add(new Available(a.getX(), 7 - a.getY(), board, a.getA()));
		return newList;
	}

	public void move(int new_x, int new_y, Board board) {
		Piece target = board.getPiece(new_x, new_y);
		if (target != null)
			board.pieces.remove(target);
		this.x = new_x;
		this.y = new_y;
	}

	public void safeMove(int new_x, int new_y, Board board) {
		this.x = new_x;
		this.y = new_y;
	}

	public void render(Board board) {
		sprite = new Sprite(texture);
		sprite.setSize(size, size);
		float rx = board.getX();
		float ry = board.getY();
		sprite.setX(rx + size * x);
		sprite.setY(ry + size * y);
		sprite.draw(OnlineChess.batch);
	}

	public void dispose() {
		texture.dispose();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public float getRealX() {
		return sprite.getX();
	}

	public float getRealY() {
		return sprite.getY();
	}

	protected boolean valid(int x, int y) {
		if (x > -1 && x < 8)
			if (y > -1 && y < 8)
				return true;
		return false;
	}

}
