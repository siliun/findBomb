package findBomb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 지뢰찾기 결과값 생성기능
public class Main {
	
	private final static int xAxis = 10; // 박스 가로개수
	private final static int yAxis = 10; // 박스 새로개수
	private final static int bombCount = 10; // 지뢰개수
	private final static int bombVal = -9;	// 지뢰(폭탄)값
			
	public static void main(String[] args) {
		
		// 박스 생성
		Integer[][] mineBox = createMineBox();
		
		// 10X10 배열로 결과출력
		for(int y = yAxis-1 ; y > -1; y--) {
			for(int x = 0; x < xAxis ; x++ ) {
				if(mineBox[x][y] > -1) {
					System.out.print(" "+mineBox[x][y]);
				} else {
					System.out.print(mineBox[x][y]);
				}
				if(x < xAxis-1) {
					System.out.print(",");
				} 
			}
			System.out.print("\n");
		}
		
	}
	
	// 박스 생성 (가로개수, 세로개수, 지뢰개수)
	public static Integer[][] createMineBox(){
		
		// 가로,세로 값으로 2차 배열 생성(0 으로 채우기)
		Integer[][] mineBox = makeArray();
		
		// 지뢰자표 랜덤 생성
		mineBox = makeBomb(mineBox);
		
		// 지뢰개수 생성
		mineBox = makeNum(mineBox);		
				
		return mineBox;
	}
	
	// 가로,세로 값으로 2차 배열 생성(0 으로 채우기)
	public static Integer[][] makeArray (){
		
		Integer[][] makeArrayBox = new Integer[xAxis][yAxis];	// 리턴값 초기화
		
		for(int x = 0; x < xAxis; x++) {
			for(int y = 0; y < yAxis; y++) {
				makeArrayBox[x][y] = 0;
			}
		}
		
		return makeArrayBox;
	}
	
	// 지뢰 자표생성
	public static Integer[][] makeBomb(Integer[][] range){

		Integer[][] makeBombBox = range;	// 리턴값 초기화
		List<String> bombAxisList = new ArrayList<String>();	// 지뢰좌표 목록 선언
		Random rd = new Random();	// 랜덤함수 선언
		
		// 지뢰개수만큼 좌표목록 생성
		for(int bombIdx = 0; bombIdx < bombCount; bombIdx++) {
			// 중복검사를 위한 반복처리
			while(true){
				// 좌표생성
				String axis = rd.nextInt(xAxis)+","+rd.nextInt(yAxis);
				
				// 생성된 좌표 추가
				bombAxisList.add(axis);
				
				// 생성된 좌표 중복 검사
				if(bombAxisList.stream().distinct().count() != bombAxisList.size()) {
					// 중복된 좌표가 있을경우 마지막 좌표 삭제후 재생성(에러)
					bombAxisList.remove(bombIdx);
				} else {
					// 중복되지 않으면 계속진행(정상)
					break;
				}
				
			}// while end
			
		}	// for end
						
		// 생성된 좌료목록에 폭탄값 삽입
		for(String axis : bombAxisList) {
			// 가로,세로 분리
			String[] arrAxis = axis.split(",");
			// 좌표에 폭탄값 삽입
			makeBombBox[Integer.parseInt(arrAxis[0])][Integer.parseInt(arrAxis[1])] = bombVal;
		}
		
		return makeBombBox;
		
	}
			
	// 지뢰개수 생성
	public static Integer[][] makeNum(Integer[][] range){

		Integer[][] makeNumBox = range;	// 리턴값 초기화

		// 박스범위 
		for(int x = 0; x < xAxis; x++) {
			for(int y = 0; y < yAxis; y++) {

				int axisVal = makeNumBox[x][y];	// 박스별 값 조회
				
				// 지뢰가 존재하는 칸인경우 주위 숫자 입력
				if (axisVal == bombVal) {
					// 기준값으로 우좌 좌표 가져오기
					for(int px = x+1; px > x-2 ; px--) {
						if(px > -1 && px < xAxis) {	// 박스범위를 벗어나지 않게
							// 기준값으로 상하 좌표가져오기
							for(int py = y+1; py > y-2; py--) {
								if(py > -1 && py < yAxis) {	// 박스범위를 벗어나지 않게
									if(makeNumBox[px][py] > -1) {
										makeNumBox[px][py]++;	// 주위 지뢰개수 누적
									}
								}
							}
						}
					}					
				} // if(axisVal == bombVal) end
				
			} // for(j) end
		} // for(i) end
		
		return makeNumBox;
	}
	
}

