package Exam.Twenty.Resit;

import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentClusteringTest {

	@Test
	public void legalisingDocuments() {
		int n = 4;
		int k = 2;
		int[][] c = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 100, 100 }, { 0, 1, 0, 1, 100 }, { 0, 100, 1, 0, 1 }, { 0, 100, 100, 1, 0 } };
		// All false, Phoenix had no time to do this yet.
		boolean[][] e = new boolean[n + 1][n + 1];
		// Whatever grouping of two groups we make, the smallest score between two groups is always 1
		assertEquals(1, DocumentClustering.legalisingDocuments(n, k, c, e));
	}

	@Test
	public void keepGivenGroupsFromPhoenix() {
		int n = 4;
		int k = 2;
		int[][] c = { { 0, 0, 0, 0, 0 }, { 0, 0, 2, 3, 4 }, { 0, 2, 0, 1, 100 }, { 0, 3, 1, 0, 1 }, { 0, 4, 100, 1, 0 } };
		boolean[][] e = new boolean[n + 1][n + 1];
		e[2][4] = e[4][2] = true;
		// Group A: 2,3,4; Group B: 1; Smallest similarity between groups: 1 & 2 with 2 which has maximised the smallest score between groups (so minimised the similarity score inside groups).
		assertEquals(2, DocumentClustering.legalisingDocuments(n, k, c, e));
	}
}