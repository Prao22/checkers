package Utility;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import Utility.BoardCreator.Corners;

public class CornerCorrectnessTest {

	final int[][] A4 = {{0,6},{1,5},{1,6},{2,5},{2,6},{2,7},{3,4},{3,5},{3,6},{3,7}};
	final int[][] C4 = {{12,0},{12,1},{11,0},{12,2},{11,1},{10,1},{12,3},{11,2},{10,2},{9,1}};
	final int[][] F4 = {{4,12},{4,11},{5,11},{4,10},{5,10},{6,11},{4,9},{5,9},{6,10},{7,10}};
	
	final int[][] B7 = {{7,0},{7,1},{8,1},{7,2},{8,2},{9,1},{7,3},{8,3},{9,2},{10,2},{7,4},
			{8,4},{9,3},{10,3},{11,2},{7,5},{8,5},{9,4},{10,4},{11,3},{12,3},{7,6},
			{8,6},{9,5},{10,5},{11,4},{12,4},{13,3}};
	final int[][] D7 = {{28,11},{27,11},{27,10},{26,12},{26,11},{26,10},{25,12},{25,11},{25,10},{25,9},{24,13},
			{24,12},{24,11},{24,10},{24,9},{23,13},{23,12},{23,11},{23,10},{23,9},{23,8},{22,14},
			{22,13},{22,12},{22,11},{22,10},{22,9},{22,8}};
	final int[][] E7 = {{21,21},{21,20},{20,21},{21,19},{20,20},{19,20},{21,18},{20,19},{19,19},{18,20},{21,17},
			{20,18},{19,18},{18,19},{17,19},{21,16},{20,17},{19,17},{18,18},{17,18},{16,19},{21,15},
			{20,16},{19,16},{18,17},{17,17},{16,18},{15,18}};
	
	final int[][] A8 = {{0,12},{1,11},{1,12},{2,11},{2,12},{2,13},{3,10},{3,11},{3,12},{3,13},{4,10},
			{4,11},{4,12},{4,13},{4,14},{5,9},{5,10},{5,11},{5,12},{5,13},{5,14},{6,9},
			{6,10},{6,11},{6,12},{6,13},{6,14},{6,15},{7,8},{7,9},{7,10},{7,11},{7,12},
			{7,13},{7,14},{7,15}};
	final int[][] B8 = {{8,0},{8,1},{9,0},{8,2},{9,1},{10,1},{8,3},{9,2},{10,2},{11,1},{8,4},
			{9,3},{10,3},{11,2},{12,2},{8,5},{9,4},{10,4},{11,3},{12,3},{13,2},{8,6},
			{9,5},{10,5},{11,4},{12,4},{13,3},{14,3},{8,7},{9,6},{10,6},{11,5},{12,5},
			{13,4},{14,4},{15,3}};
	final int[][] C8 = {{24,0},{24,1},{23,0},{24,2},{23,1},{22,1},{24,3},{23,2},{22,2},{21,1},{24,4},
			{23,3},{22,3},{21,2},{20,2},{24,5},{23,4},{22,4},{21,3},{20,3},{19,2},{24,6},
			{23,5},{22,5},{21,4},{20,4},{19,3},{18,3},{24,7},{23,6},{22,6},{21,5},{20,5},
			{19,4},{18,4},{17,3}};
	final int[][] D8 = {{32,12},{31,12},{31,11},{30,13},{30,12},{30,11},{29,13},{29,12},{29,11},{29,10},{28,14},
			{28,13},{28,12},{28,11},{28,10},{27,14},{27,13},{27,12},{27,11},{27,10},{27,9},{26,15},
			{26,14},{26,13},{26,12},{26,11},{26,10},{26,9},{25,15},{25,14},{25,13},{25,12},{25,11},
			{25,10},{25,9},{25,8}};
	final int[][] E8 = {{24,24},{24,23},{23,23},{24,22},{23,22},{22,23},{24,21},{23,21},{22,22},{21,22},{24,20},
			{23,20},{22,21},{21,21},{20,22},{24,19},{23,19},{22,20},{21,20},{20,21},{19,21},{24,18},
			{23,18},{22,19},{21,19},{20,20},{19,20},{18,21},{24,17},{23,17},{22,18},{21,18},{20,19},
			{19,19},{18,20},{17,20}};
	final int[][] F8 = {{8,24},{8,23},{9,23},{8,22},{9,22},{10,23},{8,21},{9,21},{10,22},{11,22},{8,20},
			{9,20},{10,21},{11,21},{12,22},{8,19},{9,19},{10,20},{11,20},{12,21},{13,21},{8,18},
			{9,18},{10,19},{11,19},{12,20},{13,20},{14,21},{8,17},{9,17},{10,18},{11,18},{12,19},
			{13,19},{14,20},{15,20}};
	@Test
	public void CornersFor4() {
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.A, BoardCreator.createBoard(4), 4), A4)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.C, BoardCreator.createBoard(4), 4), C4)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.F, BoardCreator.createBoard(4), 4), F4)) throw new AssertionError();
	}
	
	@Test
	public void CornersFor7() {
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.B, BoardCreator.createBoard(7), 7), B7)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.D, BoardCreator.createBoard(7), 7), D7)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.E, BoardCreator.createBoard(7), 7), E7)) throw new AssertionError();
	}
	
	@Test
	public void CornersFor8() {
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.A, BoardCreator.createBoard(8), 8), A8)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.B, BoardCreator.createBoard(8), 8), B8)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.C, BoardCreator.createBoard(8), 8), C8)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.D, BoardCreator.createBoard(8), 8), D8)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.E, BoardCreator.createBoard(8), 8), E8)) throw new AssertionError();
		if (!Arrays.deepEquals(BoardCreator.getCorner(Corners.F, BoardCreator.createBoard(8), 8), F8)) throw new AssertionError();
	}

}
