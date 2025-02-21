package com.tj703.web_app_server_study;

import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HexFormat;

public class L20HashCode {
    public static void main(String[] args) throws Exception {
        //단반양 암호화 함수
        //MD5, SHA1, SHA2
        //해시코드 만드는 방법 :'1234' -> 수로 바꿔서 더하고 빼고 보수.. 연산을 계속해서 특정 길이의 값을 반환
        //비트코인 == 해시코드
        //해시코드의 문제점 같은 값이 존재할 수도 있다, 연산하는 방법을 알아내면 그 값을 역추리 할 수 있다.
        //'안녕' == '1234'
        MessageDigest digest=MessageDigest.getInstance("MD5");
        String pw="안녕"; //->문자표의 번호
        byte[] pwBytes=pw.getBytes();
        System.out.println(Arrays.toString(pwBytes));
        System.out.println(new BigInteger(1,pwBytes).toString(16));
        //"안녕" == ec 95 88   eb 85 95
        //byte=-128 ~ 127;
        // 0~255 == 1byte
        byte b=(byte) 128; //=> -128
        byte [] pwMd5=(digest.digest(pwBytes));
        System.out.println(Arrays.toString(pwMd5));
        //bigint : 길이가 없는 정수 (string 비슷)
        BigInteger pwMd4BigInt=new BigInteger(1,pwMd5);
        System.out.println(pwMd4BigInt);
        System.out.println(pwMd4BigInt.toString(16));
        //안녕 md5 => ef1c511c9efb9259438f098aff940740
        //////////////////////
        //sha-1 128bit
        MessageDigest sha1=MessageDigest.getInstance("SHA-1");
        String pwSha1=new BigInteger(1,sha1.digest(pw.getBytes())).toString(16);
        System.out.println(pwSha1);
        //안녕 sha1 => 4afa71ffb32dc64586c1a3b65cb7b99d7f2e3185
        //sha-2 (256(32byte), 512)
        MessageDigest sha256=MessageDigest.getInstance("SHA-256");
        String pwSha256=new BigInteger(1,sha256.digest(pw.getBytes())).toString(16);
        System.out.println(pwSha256);
        //안녕 sha256 => e8f817f346d1d411cc59d5bdda64fab3763890e1f0f8f4c15805cf78874d68bf
        //데이터베이스의 pw 길이는 64
        //Bcrypt (솔트 + 유연성 있는 연산 + 연계연산)
        //솔트를 직접 지정할 수 있는데 권장하지 않고 자동생성 권장함
        String pwBcrypt=BCrypt.hashpw(pw,BCrypt.gensalt());
        System.out.println(pwBcrypt);
        //$2a$10$A528qVRV.8EKjTG8GSt17eH2uJkyuo9WDYubatqX0Z0akYZSfdoD.
        //$2a $10 $A528qVRV. 8EKjTG8GSt17eH2uJkyuo9WDYubatqX0Z0akYZSfdoD.
        String pwBcrypt2=(BCrypt.hashpw(pw,BCrypt.gensalt()));
        System.out.println(pwBcrypt2);
        //$2a $10 $4SID6WQBL0/DVgxJlV84xeIxH. 0GFLgAhOZKvURb5M0rGu5oWLgGe
        //2a BCrypt 해시함수의 버전
        //10 몇번 반복해서 해시 함수를 만들었나
        //A528qVRV 솔트 해시함수를 만들때 사용한 키
        //매번 솔트와 연산 수에 의해 다른 값이 반환 (키와 연산 수가 공개됨 ==공개키)
        System.out.println(BCrypt.checkpw("안녕",pwBcrypt));
        System.out.println(BCrypt.checkpw("안녕",pwBcrypt2));
        System.out.println(BCrypt.checkpw("1234",pwBcrypt));

        //MD5 SHA1 SHA2의 취약점 (동일한값, 해시함수의 연산이 수평적으로 일어남)
        //1. 동일한 값 => 무작위 값 대입(무차별 대입) => 어쩌다 1개 걸림
        //2. 수평적으로 연산할 수 있는 GPU를 이용해 해시함수를 풀어버림 (레이보우 테이블)
        //cpu : 연산을 순서대로 처리,  gpu : 한꺼번에 연산을 처리

        //BCrypt
        //1. 무차별대입이 불가능 "1234"->AOWQq, "1234"->1iES5Ejwq8hLk3
        //2. 솔트로 순자적 연산을 해야 해시 함수가 나옴 => 수평적 연산이 불가능

        //키가 노출되면 안되기 때문에 잘 사용하지 않음
        //#AES(대칭키) ,RSA(비대칭키) Cipher
        //"안녕" => 해시함수 (암호화)  key="비밀번호"
        //해시함수 => "안녕"         key="비밀번호123"

        System.out.println(BCrypt.hashpw("1234",BCrypt.gensalt()));
        //$2a$10$2cAGFCZKaya/tR2CokiYzuJZMukEg.xZsqW6jxgKjat6v3fwfMGQe <="1234"
        //==$2a$10$xKugCxho.gezNaZYUZm7t.rW8ply8Z7WcV53psL0HU3YbGhIQsjna <="1234"
        String hash1234_1="$2a$10$2cAGFCZKaya/tR2CokiYzuJZMukEg.xZsqW6jxgKjat6v3fwfMGQe";
        String hash1234_2="$2a$10$xKugCxho.gezNaZYUZm7t.rW8ply8Z7WcV53psL0HU3YbGhIQsjna";
        //"1234" 평문
        //db에 저장된 값을 그대로 쿠키로 사용해야함
        System.out.println(BCrypt.checkpw(hash1234_1,hash1234_2));

    }
}
