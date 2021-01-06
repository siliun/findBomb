package findBomb;

import java.util.Random;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		// 숫자 입력
		int x = 10; // 가로개수
		int y = 10; // 세로개수
		int z = 10; // 지뢰개수
		
		// 박스 생성
		int[][] box = createBox(x,y,z);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++ ) {
				if(box[i][j] > -1) {
					System.out.print(" "+box[i][j]);
				} else {
					System.out.print(box[i][j]);
				}
				if(j < 9) {
					System.out.print(",");
				} 
			}
			System.out.print("\n");
		}
		
	}
	
	// 박스 생성 (가로개수, 세로개수, 지뢰개수)
	public static int[][] createBox(int x, int y, int z){
		
		// x, y 배열 생성(0)
		int[][] mainBox = new int[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				mainBox[i][j] = 0;
			}
		}
		
		// 지뢰자표 랜덤 생성(-99)
		Random rd = new Random();
		
		for(int i = 0; i < z; i++) {
			mainBox[rd.nextInt(x)][rd.nextInt(y)] = -9;			
		}
		
		
		// 지뢰갯수 구함
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
			// 한칸당			
				int p = mainBox[i][j];
				if (p == -9) {
					for(int k = i+1; k > i-2 ; k--) {
						if(k > -1 && k < x) {
							for(int l = j+1; l > j-2; l--) {
								if(l > -1 && l < y) {
									if(mainBox[k][l] > -1) {
										mainBox[k][l]++;
									}
								}
							}
						}
					}
					
				} // if end
			} // for(j) end
		} // for(i) end
		
				
		return mainBox;
	}
	
}

