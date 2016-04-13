import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	private static FastReader in = new FastReader();
	private static char SPACE_CHAR = 'O';
	private static int SPACE = 1;
	private static int BLOCK = 0;
	private static int UP = 0;
	private static int LEFT = 1;
	private static int RIGHT = 2;
	private static int DOWN = 3;
	private static int COUNT = 4;
	private static int[][] MOVE = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.run();
	}

	void run() {
		int n = in.nextInt();

		int[][] field = new int[n + 2][n + 2];
		for (int i = 1; i < n + 1; i++) {
			char[] chars = in.next().toCharArray();
			for (int j = 0; j < chars.length; j++) {
				field[i][j + 1] = chars[j] == SPACE_CHAR ? SPACE : BLOCK;
			}
		}

		int[][][] memo = new int[n + 2][n + 2][5];
		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo.length; j++) {
				if (field[i][j] == SPACE) {
					int[] direction = memo[i][j];
					direction[UP] = -1;
					direction[LEFT] = -1;
					direction[RIGHT] = -1;
					direction[DOWN] = -1;
					direction[COUNT] = -1;
				}
			}
		}

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				startSearchAt(i, j, field, copyMemo(memo));
			}
		}
	}

	private void startSearchAt(int i, int j, int[][] field, int[][][] memo) {
		
	}

	private int[][][] copyMemo(int[][][] memo) {
		return Arrays.stream(memo)
				.map(two -> Arrays.stream(two)
						.map(int[]::clone)
						.collect(Collectors.toList())
						.toArray(new int[][]{}))
				.collect(Collectors.toList())
				.toArray(new int[][][]{});
	}
}

class FastReader {
	private InputStream in = System.in;
	private byte[] buf = new byte[1024];
	private int charNum;
	private int charLen;
	private StringBuilder sb = new StringBuilder();

	public int read() {
		if (charLen == -1)
			throw new InputMismatchException();
		if (charNum >= charLen) {
			charNum = 0;
			try {
				charLen = in.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (charLen <= 0)
				return -1;
		}
		return buf[charNum++];
	}

	public String next() {
		int c = read();
		while (isWhitespace(c)) {
			c = read();
		}
		sb.setLength(0);
		do {
			sb.appendCodePoint(c);
			c = read();
		} while (!isWhitespace(c));
		return sb.toString();
	}

	public char[] nextCharArray() {
		return next().toCharArray();
	}

	public int nextInt() {
		return (int) nextLong();
	}

	public int[] nextIntArray(int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = nextInt();
		return array;
	}

	public List<Integer> nextIntList(int n) {
		Integer[] array = new Integer[n];
		for (int i = 0; i < n; i++)
			array[i] = nextInt();
		return Arrays.asList(array);
	}

	public int[][] nextIntArray2D(int n, int m) {
		int[][] array = new int[n][m];
		for (int i = 0; i < n; i++)
			array[i] = nextIntArray(m);
		return array;
	}

	public List<int[]> nextIntsList(int n, int m) {
		List<int[]> list = new ArrayList<int[]>(n);
		for (int i = 0; i < n; i++)
			list.add(nextIntArray(m));
		return list;
	}

	public long nextLong() {
		int c = read();
		while (isWhitespace(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isWhitespace(c));
		return res * sgn;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public double[] nextDoubleArray(int n) {
		double[] array = new double[n];
		for (int i = 0; i < n; i++)
			array[i] = nextDouble();
		return array;
	}

	public boolean isWhitespace(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}
}
