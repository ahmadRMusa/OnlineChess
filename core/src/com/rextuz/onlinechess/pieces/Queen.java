package com.rextuz.onlinechess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.rextuz.onlinechess.Board;
import com.rextuz.onlinechess.OnlineChess;

public class Queen extends Piece {
	private static final long serialVersionUID = 1L;

	public Queen(int x, int y, String color, Board board) {
		super(x, y, color, board);
		texture = new Texture("queen_" + color + ".png");
	}

	@Override
	public List<Available> getMoves() {
		List<Available> list = new ArrayList<Available>();
		int xt, yt;
		xt = x + 1;
		yt = y;
		while (xt < 8 && OnlineChess.board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			xt++;
		}
		if (board.getPiece(xt, yt) != null)
			if (!board.getPiece(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));
		xt = x - 1;
		while (xt > -1 && OnlineChess.board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			xt--;
		}
		if (board.getPiece(xt, yt) != null)
			if (!board.getPiece(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));
		xt = x;
		yt = y + 1;
		while (yt < 8 && OnlineChess.board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			yt++;
		}
		if (board.getPiece(xt, yt) != null)
			if (!board.getPiece(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));
		yt = y - 1;
		while (yt > -1 && OnlineChess.board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			yt--;
		}
		if (board.getPiece(xt, yt) != null)
			if (!board.getPiece(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));

		xt = x + 1;
		yt = y + 1;
		while (valid(xt, yt) && board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			xt++;
			yt++;
		}
		if (valid(xt, yt))
			if (!board.pieces.get(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));

		xt = x - 1;
		yt = y - 1;
		while (valid(xt, yt) && board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			xt--;
			yt--;
		}
		if (valid(xt, yt))
			if (!board.pieces.get(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));

		xt = x + 1;
		yt = y - 1;
		while (valid(xt, yt) && board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			xt++;
			yt--;
		}
		if (valid(xt, yt))
			if (!board.pieces.get(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));

		xt = x - 1;
		yt = y + 1;
		while (valid(xt, yt) && board.cellEmpty(xt, yt)) {
			list.add(new Available(xt, yt, board, 0));
			xt--;
			yt++;
		}
		if (valid(xt, yt))
			if (!board.pieces.get(xt, yt).getColor().equals(color))
				list.add(new Available(xt, yt, board, 1));

		return list;
	}
}
