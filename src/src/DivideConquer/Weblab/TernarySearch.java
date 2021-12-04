package DivideConquer.Weblab;

public class TernarySearch {

	/**
	 * Finds the x coordinate with the highest value of an array with a unimodal function, by recursively evaluating the values at one-third and two-thirds of the range.
	 * Depending on which is higher, a new evaluation is made with a smaller range to find the x coordinate with the highest value.
	 *
	 * @param altitude the array with the unimodal function
	 * @return the x coordinate with the highest value
	 */
	public static int findPictureTime(double[] altitude) {
		return findPictureTime(altitude, 0, altitude.length - 1);
	}

	public static /**
	 * Finds the x coordinate with the highest value of an array with a unimodal function, by recursively evaluating the values at one-third and two-thirds of the range.
	 * Depending on which is higher, a new evaluation is made with a smaller range to find the x coordinate with the highest value.
	 * @param altitude the array with the unimodal function
	 * @param a lower bound on the x coordinate
	 * @param an upper bound on the x coordinate
	 * @return the x coordinate with the highest value
	 */
	int findPictureTime(double[] altitude, int low, int high) {
		if (high - low < 3) {
			if (altitude[low + 1] > altitude[low] && altitude[low + 1] > altitude[high]) return low + 1;
			else if (altitude[high] > altitude[low]) return high;
			else return low;
		}

		int third = (high - low) / 3;
		int mid1 = low + third;
		int mid2 = high - third;
		if (altitude[mid1] > altitude[mid2]) return findPictureTime(altitude, low, mid2);
		else return findPictureTime(altitude, mid1, high);
	}
}
