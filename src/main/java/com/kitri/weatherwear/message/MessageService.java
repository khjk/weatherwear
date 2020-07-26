package com.kitri.weatherwear.message;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
* 하드코딩....EVA....
* */
@Service
public class MessageService {
    private static List<Message> messages = new ArrayList<>();
    private static List<String> messageWithCode1 = new ArrayList<>();
    private static List<String> messageWithCode2 = new ArrayList<>();
    private static List<String> messageWithCode3 = new ArrayList<>();
    private static List<String> messageWithCode4 = new ArrayList<>();
    private static List<String> messageWithCode5 = new ArrayList<>();
    private static List<String> messageWithCode6 = new ArrayList<>();
    private static List<String> messageWithCode7 = new ArrayList<>();
    private static List<String> messageWithCode8 = new ArrayList<>();
    private static final int EXTRA_CODE_RAIN = 9;
    private static final int EXTRA_CODE_SNOW = 10;
    private static List<String> messageWithCodeRain = new ArrayList<>();
    private static List<String> messageWithCodeSnow = new ArrayList<>();
    static {
        //28도이상 CODE1
        messageWithCode1.add("가만히 있어도 땀이 흐르는 날씨에요. 옷차림을 더 가볍게 해볼까요?");
        messageWithCode1.add("매우 더운 날씨입니다. 반팔과 반바지는 필수입니다!");
        messageWithCode1.add("덥다고 말하기도 덥네요...최대한 가볍게 입기!");
        messages.add(new Message(1,messageWithCode1));
        //23도이상 CODE2
        messageWithCode2.add("더운걸까요... 쾌적함을 위해 가벼운 반팔을 입어보세요!");
        messageWithCode2.add("덥지만 활동하기에는 좋은 날씨입니다.");
        messageWithCode2.add("시원한 반팔 추천합니다. 에어컨 있는 실내에 오래 있다면 가벼운 가디건도 하나 챙겨주세요:)");
        messages.add(new Message(2,messageWithCode2));
        //20도이상 CODE3
        messageWithCode3.add("적당한 날씨네요! 반팔에 얇은 가디건이나 가벼운 긴팔 어떠세요?");
        messageWithCode3.add("가디건이나 긴팔을 입고 밖에 나가 활동하기에 좋은 날씨입니다.");
        messageWithCode3.add("초여름, 초가을 날씨에요. 긴팔티나 얇은 가디건 추천합니다~");
        messages.add(new Message(3,messageWithCode3));
        //17도이상 CODE4
        messageWithCode4.add("가을 날씨네요! 얇은 니트, 맨투맨이 어울릴 것 같아요~?");
        messageWithCode4.add("선선한 날씨입니다. 긴바지와 맨투맨을 추천해요!");
        messageWithCode4.add("나들이하기 딱 좋은 날씨네요~ 아우터 없이 외출해도 좋겠어요!");
        messages.add(new Message(4,messageWithCode4));
        //12도이상 CODE5
        messageWithCode5.add("자켓이나 야상 등 외투가 필요한 날씨입니다. 나가기 전에 꼭 챙겨주세요!");
        messageWithCode5.add("아우터 하나만 입어도 좋은 날씨에요.");
        messageWithCode5.add("자켓이나 가디건 단독 가능! 바지는 긴바지!");
        messages.add(new Message(5,messageWithCode5));
        //9도이상 CODE6
        messageWithCode6.add("가벼운 코트나 니트가 어울리는 계절이 왔어요! 패션을 뽐내볼까요?");
        messageWithCode6.add("트렌치 코트의 계절이 돌아왔습니다. 지금만 입을 수 있으니 옷장에서 꺼내보아요!");
        messageWithCode6.add("트렌치코트, 야상, 가죽자켓 괜츈! 대신 안에 좀 껴입어주세요~");
        messages.add(new Message(6,messageWithCode6));
        //5도이상 CODE7
        messageWithCode7.add("살짝 춥네요. 슬슬 옷장에서 두꺼운 옷들을 꺼내볼까요?");
        messageWithCode7.add("핸드메이드 코트, 경량패딩 추전합니다!");
        messageWithCode7.add("추위 많이 타시면 히트텍 챙겨입어주세요~");
        messages.add(new Message(7,messageWithCode7));
        //5도미만(영하포함) CODE8
        messageWithCode8.add("날씨가 많이 쌀쌀하네요! 목도리나 패딩 필수에요!");
        messageWithCode8.add("롱패딩의 계절입니다. 감기에 걸리지 않도록 따뜻한 옷을 입어주세요!");
        messageWithCode8.add("무조건 패딩...! 코트 추워요.. 따뜻한 옷 모조리 껴입으세요!");
        messages.add(new Message(8, messageWithCode8));
        //비올때 CODE9
        messageWithCodeRain.add("오늘은 비가 내리네요. 우산 꼭 챙기세요~");
        messageWithCodeRain.add("Rainy day:)");
        messageWithCodeRain.add("빗길 운전 조심! 미끄럼 조심!");
        messages.add(new Message(EXTRA_CODE_RAIN, messageWithCodeRain));
        //눈올때 CODE10
        messageWithCodeSnow.add("눈온다~~~><");
        messageWithCodeSnow.add("오늘은 눈이 오네요. 운전 조심하세요~");
        messageWithCodeSnow.add("눈이 와요! 오늘은 뭔가 좋은 일이 일어날 것만 같아요~!");
        messages.add(new Message(EXTRA_CODE_SNOW, messageWithCodeSnow));
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public String getRandomMessageByCode(int code) {
        Iterator<Message> iterator = messages.iterator();
        List<String> messageWithCode;
        while (iterator.hasNext()) {
            Message message = iterator.next();

            if(message.getTemp_code() == code) {
                messageWithCode = message.getMessage();
                int randomIndex = (int)(Math.random()*3);
                return messageWithCode.get(randomIndex);
            }
        }
        return null; //이상한 코드로 호출할 때
    }
}
