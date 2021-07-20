package com.latte.lotto

import android.app.Application
import com.latte.lotto.database.NumberHistoryRepository

//서브 클래스 manifest에도 지정.
//최초 실행될때 메모리에 로드되며 딱 한번 실행된다.
//즉 DB 인스턴스를 한번만 생성한다.
class LottoIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NumberModel.init() //번호 데이터 정보.
        NumberHistoryRepository.init(this)
    }
}