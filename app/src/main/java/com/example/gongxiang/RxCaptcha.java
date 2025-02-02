package com.example.gongxiang;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

    public class RxCaptcha {
        //随机数数组
        private static final char[] CHARS = {
                '1','2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
                'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        private static com.example.gongxiang.RxCaptcha bmpCode;
        public static com.example.gongxiang.RxCaptcha getInstance() {
            if(bmpCode == null)
                bmpCode = new com.example.gongxiang.RxCaptcha();
            return bmpCode;
        }
        //default settings
        //验证码默认随机数的个数
        private static final int DEFAULT_CODE_LENGTH = 4;
        //默认字体大小
        private static final int DEFAULT_FONT_SIZE = 25;
        //默认线条的条数
        //padding值
        private static final int DEFAULT_LINE_NUMBER = 3;
        private static final int BASE_PADDING_LEFT = 10,
                RANGE_PADDING_LEFT = 15,
                BASE_PADDING_TOP = 15,
                RANGE_PADDING_TOP = 20;
        //验证码的默认宽高
        private static final int DEFAULT_WIDTH = 100,
                DEFAULT_HEIGHT = 40;
        private int width = DEFAULT_WIDTH,
                height = DEFAULT_HEIGHT;
        private int base_padding_left = BASE_PADDING_LEFT,
                range_padding_left = RANGE_PADDING_LEFT,
                base_padding_top = BASE_PADDING_TOP,
                range_padding_top = RANGE_PADDING_TOP;
        private int codeLength = DEFAULT_CODE_LENGTH,
                line_number = DEFAULT_LINE_NUMBER,
                font_size = DEFAULT_FONT_SIZE;
        private String code;
        private int padding_left, padding_top;
        private Random random = new Random();
        //验证码图片
        public Bitmap createBitmap() {
            padding_left = 0;
            Bitmap bp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bp);
            code = createCode();
            c.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setAntiAlias(true);//是否抗锯齿
            paint.setTextSize(font_size);
            //画验证码
            for (int i = 0; i < code.length(); i++) {
                randomTextStyle(paint);
                randomPadding();
                c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
            }
            //画线条
            for (int i = 0; i < line_number; i++) {
                drawLine(c, paint);
            }
            c.save();//保存
            c.restore();
            return bp;
        }
        public String getCode() {
            return code;
        }
        //生成验证码
        private String createCode() {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < codeLength; i++) {
                buffer.append(CHARS[random.nextInt(CHARS.length)]);
            }
            return buffer.toString();
        }
        //画干扰线
        private void drawLine(Canvas canvas, Paint paint) {
            int color = randomColor();
            int startX = random.nextInt(width);
            int startY = random.nextInt(height);
            int stopX = random.nextInt(width);
            int stopY = random.nextInt(height);
            paint.setStrokeWidth(1);
            paint.setColor(color);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
        //生成随机颜色
        private int randomColor() {
            return randomColor(1);
        }
        private int randomColor(int rate) {
            int red = random.nextInt(256) / rate;
            int green = random.nextInt(256) / rate;
            int blue = random.nextInt(256) / rate;
            return Color.rgb(red, green, blue);
        }
        //随机生成文字样式，颜色，粗细，倾斜度
        private void randomTextStyle(Paint paint) {
            int color = randomColor();
            paint.setColor(color);
            paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体
            float skewX = random.nextInt(11) / 10;
            skewX = random.nextBoolean() ? skewX : -skewX;  //
            paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
        }

        //随机生成padding值
        private void randomPadding() {
            padding_left += base_padding_left + random.nextInt(range_padding_left);
            padding_top = base_padding_top + random.nextInt(range_padding_top);
        }
    }

    //第二种验证码方法
//public class RxCaptcha {

//        public static RxCaptcha build() {
//            if (rxCaptcha == null) {
//                rxCaptcha = new RxCaptcha();
//            }
//            return rxCaptcha;
//        }
//
//        private static final char[] CHARS_NUMBER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
//
//        private static final char[] CHARS_LETTER = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
//                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
//                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
//                'X', 'Y', 'Z'};
//
//        private static final char[] CHARS_ALL = {'0', '1', '2', '3', '4', '5', '6',
//                '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
//                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
//                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
//                'X', 'Y', 'Z'};
//
//        private static RxCaptcha rxCaptcha;
//
//        private TYPE type = TYPE.CHARS;
//
//        public enum TYPE{
//            NUMBER,LETTER,CHARS
//        }
//
//        private RxCaptcha() {
//
//        }
//
//        private RxCaptcha(TYPE types) {
//            this.type = types;
//        }
//
//        public static RxCaptcha getInstance(TYPE types) {
//            if (rxCaptcha == null) {
//                rxCaptcha = new RxCaptcha(types);
//            }
//            return rxCaptcha;
//        }
//
//        // default settings
//        private static final int DEFAULT_CODE_LENGTH = 4;// 验证码的长度 这里是4位
//        private static final int DEFAULT_FONT_SIZE = 60;// 字体大小
//        private static final int DEFAULT_LINE_NUMBER = 0;// 多少条干扰线
//        private static final int BASE_PADDING_LEFT = 20; // 左边距
//        private static final int RANGE_PADDING_LEFT = 20;// 左边距范围值
//        private static final int BASE_PADDING_TOP = 42;// 上边距
//        private static final int RANGE_PADDING_TOP = 15;// 上边距范围值
//        private static int DEFAULT_WIDTH = 200;// 默认宽度.图片的总宽
//        private static int DEFAULT_HEIGHT = 70;// 默认高度.图片的总高
//        private int DEFAULT_COLOR = 0xdf;// 默认背景颜色值
//
//        // settings decided by the layout xml
//        // canvas width and height
//        private int width = DEFAULT_WIDTH;
//        private int height = DEFAULT_HEIGHT;
//
//        // random word space and pading_top
//        private int base_padding_left = BASE_PADDING_LEFT;
//        private int range_padding_left = RANGE_PADDING_LEFT;
//        private int base_padding_top = BASE_PADDING_TOP;
//        private int range_padding_top = RANGE_PADDING_TOP;
//
//        // number of chars, lines; font size
//        private int codeLength = DEFAULT_CODE_LENGTH;
//        private int line_number = DEFAULT_LINE_NUMBER;
//        private int font_size = DEFAULT_FONT_SIZE;
//
//        // variables
//        private String code;// 保存生成的验证码
//        private int padding_left, padding_top;
//        private Random random = new Random();
//
//        /**
//         * @param length 验证码的长度
//         * @return
//         */
//        public RxCaptcha codeLength(int length) {
//            codeLength = length;
//            return rxCaptcha;
//        }
//
//        /**
//         * @param size 字体大小
//         * @return
//         */
//        public RxCaptcha fontSize(int size) {
//            font_size = size;
//            return rxCaptcha;
//        }
//
//        /**
//         * @param number 干扰线 数量
//         * @return
//         */
//        public RxCaptcha lineNumber(int number) {
//            line_number = number;
//            return rxCaptcha;
//        }
//
//        /**
//         * @return 背景颜色值
//         */
//        public RxCaptcha backColor(int colorInt) {
//            DEFAULT_COLOR = colorInt;
//            return rxCaptcha;
//        }
//
//        public RxCaptcha type(TYPE type) {
//            this.type = type;
//            return rxCaptcha;
//        }
//
//        public RxCaptcha size(int width, int height) {
//            this.width = width;
//            this.height = height;
//            return rxCaptcha;
//        }
//
//        private Bitmap makeBitmap() {
//            padding_left = 0;
//
//            Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
//            Canvas c = new Canvas(bp);
//
//            code = makeCode();
//
//            c.drawColor(Color.rgb(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR));
//            Paint paint = new Paint();
//            paint.setTextSize(font_size);
//
//            for (int i = 0; i < code.length(); i++) {
//                randomTextStyle(paint);
//                randomPadding();
//                c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
//            }
//
//            for (int i = 0; i < line_number; i++) {
//                drawLine(c, paint);
//            }
//            c.save();
//            c.restore();
//            return bp;
//        }
//
//        public String getCode() {
//            return code.toLowerCase();
//        }
//
//        public Bitmap into(ImageView imageView){
//            Bitmap bitmap = createBitmap();
//            if (imageView != null) {
//                imageView.setImageBitmap(bitmap);
//            }
//            return bitmap;
//        }
//
//        public String createCode() {
//            return makeCode();
//        }
//
//        private Bitmap mBitmapCode;
//
//        public Bitmap createBitmap() {
//            mBitmapCode = makeBitmap();
//            return mBitmapCode;
//        }
//
//        private String makeCode() {
//            StringBuilder buffer = new StringBuilder();
//            switch (type) {
//                case NUMBER:
//                    for (int i = 0; i < codeLength; i++) {
//                        buffer.append(CHARS_NUMBER[random.nextInt(CHARS_NUMBER.length)]);
//                    }
//                    break;
//                case LETTER:
//                    for (int i = 0; i < codeLength; i++) {
//                        buffer.append(CHARS_LETTER[random.nextInt(CHARS_LETTER.length)]);
//                    }
//                    break;
//                case CHARS:
//                    for (int i = 0; i < codeLength; i++) {
//                        buffer.append(CHARS_ALL[random.nextInt(CHARS_ALL.length)]);
//                    }
//                    break;
//                default:
//                    for (int i = 0; i < codeLength; i++) {
//                        buffer.append(CHARS_ALL[random.nextInt(CHARS_ALL.length)]);
//                    }
//                    break;
//            }
//
//            return buffer.toString();
//        }
//
//        private void drawLine(Canvas canvas, Paint paint) {
//            int color = randomColor();
//            int startX = random.nextInt(width);
//            int startY = random.nextInt(height);
//            int stopX = random.nextInt(width);
//            int stopY = random.nextInt(height);
//            paint.setStrokeWidth(1);
//            paint.setColor(color);
//            canvas.drawLine(startX, startY, stopX, stopY, paint);
//        }
//
//        private int randomColor() {
//            return randomColor(1);
//        }
//
//        private int randomColor(int rate) {
//            int red = random.nextInt(256) / rate;
//            int green = random.nextInt(256) / rate;
//            int blue = random.nextInt(256) / rate;
//            return Color.rgb(red, green, blue);
//        }
//
//        private void randomTextStyle(Paint paint) {
//            int color = randomColor();
//            paint.setColor(color);
//            paint.setFakeBoldText(random.nextBoolean()); // true为粗体，false为非粗体
//            float skewX = random.nextInt(11) / 10;
//            skewX = random.nextBoolean() ? skewX : -skewX;
//            // paint.setTextSkewX(skewX); // float类型参数，负数表示右斜，整数左斜
//            // paint.setUnderlineText(true); //true为下划线，false为非下划线
//            // paint.setStrikeThruText(true); //true为删除线，false为非删除线
//        }
//        private void randomPadding() {
//            padding_left += base_padding_left + random.nextInt(range_padding_left);
//            padding_top = base_padding_top + random.nextInt(range_padding_top);
//        }
//}