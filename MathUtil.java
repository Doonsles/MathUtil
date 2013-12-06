public class MathUtil {
    public static int makePosative(int num){
        int answer = 0;
        for(int i = num; i < 0; i++){
            answer += 1;  
        }
        return answer;
    }

    public static int makeNegative(int num){
       int answer = 0;
       for(int i = 0; i < num; i++){
           answer -= 1;
       }
       return answer;
    }


    public static float makeFloat(String num, boolean makeAnswerNegative){
      //remove beginning 0's
      int index = 0;
      String sub = num;      

      while(index < num.length() && num.charAt(index) == '0'){
           sub = num.substring(index + 1, num.length());
           index ++;
      }

      if (makeAnswerNegative){
          sub = "-".concat(sub);
      }

      return Float.parseFloat(sub);
    }

    public static boolean checkNegs(int left, int right){
      if((left < 0 && right >= 0) || (right < 0 && left >= 0)){
        return true;
      }
      return false;
    }

    public static int add(int left, int right){
      return left + right;
    }

    public static int subtract(int left, int right){
        return left - right;
    }

    public static int multiply(int left, int right){
        boolean makeAnswerNeg = MathUtil.checkNegs(left, right);
        
        if(left < 0){
          left = MathUtil.makePosative(left);
        }
 
        if(right < 0){
          right = MathUtil.makePosative(right);
        }

        int product = 0;
        for(int i = 0; i < right; i++){
            product += left;
        }
        
        if(makeAnswerNeg){
            product = makeNegative(product);
        }

        return product;
    }
  
    public static int divide(int left, int right){
        if(right == 0){
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
       
        boolean makeAnswerNeg = MathUtil.checkNegs(left, right);       
 
        if(left < 0){
          left = MathUtil.makePosative(left);
        }

        if(right < 0){
          right = MathUtil.makePosative(right);
        }

        int quotient = 0;

         while(left >= right){
              left -= right;
              quotient ++;
         }

        if(makeAnswerNeg){
            quotient = makeNegative(quotient);
        }
         return quotient;
    }
    
    public static float divide(int left, int right, int decPlaces){
        if(decPlaces < 0){
            throw new IllegalArgumentException("Decimal places must be at least 1 for this operation");
        }

        if(right == 0){
            throw new IllegalArgumentException("Divisor cannot be zero");
        }

        boolean makeAnswerNeg = MathUtil.checkNegs(left, right);

        if(left < 0){
            left = MathUtil.makePosative(left);
        }

        if(right < 0){
            right = MathUtil.makePosative(right);
        }
         
        String stringLeft = Integer.toString(left);
        int leftIndex = 0;
        String answer = "";
        int currAnswer = 0;
        int numerator = Character.getNumericValue(stringLeft.charAt(leftIndex));

        int currDecPlaces = -1;

        while(currDecPlaces < decPlaces ){
            leftIndex ++;
            currAnswer = MathUtil.divide(numerator, right);
            answer = answer.concat(Integer.toString(currAnswer));
            numerator -= MathUtil.multiply(currAnswer, right);

          
            String newNumerator = Integer.toString(numerator);
            if (leftIndex < stringLeft.length()){
              newNumerator = newNumerator.concat(Character.toString(stringLeft.charAt(leftIndex)));
            } else {
                newNumerator = newNumerator.concat("0");
                currDecPlaces ++; 
            }
            numerator = Integer.parseInt(newNumerator);

            if(leftIndex == stringLeft.length()){
                answer = answer.concat(".");
            }
        } 
        return MathUtil.makeFloat(answer, makeAnswerNeg);
    }

    public static int power(int base, int pow){
        if(pow < 0){
          throw new IllegalArgumentException("Power must not be negative");
        }
        
        if(pow == 0){
            return 1;
        }

        int answer = 1;
        for(int i = 0; i < pow; i++){
          answer = MathUtil.multiply(answer, base);
        }
        return answer;
    }

    public static int powerRec(int base, int pow){
        if(pow < 0){
          throw new IllegalArgumentException("Power must not be negative");
        }
        if(pow == 0){
            return 1;
        } 
        return MathUtil.multiply(base, powerRec(base, pow - 1));
    }

    public static void main(String [ ] args){
        System.out.println("Multiply tests:");
        System.out.println(MathUtil.multiply(1, 4));
        System.out.println(MathUtil.multiply(4, 1));
        System.out.println(MathUtil.multiply(0, 4));
        System.out.println(MathUtil.multiply(4, 0));
        System.out.println(MathUtil.multiply(-2, 4));
        System.out.println(MathUtil.multiply(2, -4));
        System.out.println(MathUtil.multiply(-2, -4));
        System.out.println(MathUtil.multiply(1, 1));
        System.out.println(MathUtil.multiply(0, 0));
        System.out.println(MathUtil.multiply(-2, 1));
        System.out.println(MathUtil.multiply(1, -4));
        System.out.println(MathUtil.multiply(-2, 0));
        System.out.println(MathUtil.multiply(0, -4)); 
        

        System.out.println();
        System.out.println("Divide tests:");
        System.out.println(MathUtil.divide(4, 1));
        System.out.println(MathUtil.divide(0, 4));
        System.out.println(MathUtil.divide(4, -2));
        System.out.println(MathUtil.divide(-4, 2));
        System.out.println(MathUtil.divide(-4, -2));
        System.out.println(MathUtil.divide(4, 2));
        System.out.println(MathUtil.divide(2, 4));
        System.out.println(MathUtil.divide(5, 2));
        System.out.println(MathUtil.divide(5, -2));
        System.out.println(MathUtil.divide(-5, 2));   
        System.out.println(MathUtil.divide(-5, -2));

        
        System.out.println();
        System.out.println("Power tests:");
        System.out.println(MathUtil.powerRec(2, 0));
        System.out.println(MathUtil.powerRec(0,0));
        System.out.println(MathUtil.powerRec(0,2));
        System.out.println(MathUtil.powerRec(1,2));
        System.out.println(MathUtil.powerRec(2,1));
        System.out.println(MathUtil.powerRec(-2,3));
        System.out.println(MathUtil.powerRec(2,3));

        System.out.println();
        System.out.println("Divide with decimal places tests:");
        System.out.println(MathUtil.divide(4, 1, 1));
        System.out.println(MathUtil.divide(0, 4, 1));
        System.out.println(MathUtil.divide(4, -2, 2));
        System.out.println(MathUtil.divide(-4, 2, 3));
        System.out.println(MathUtil.divide(-4, -2, 4));
        System.out.println(MathUtil.divide(4, 2, 5));
        System.out.println(MathUtil.divide(2, 4, 6));
        System.out.println(MathUtil.divide(5, 2, 1));
        System.out.println(MathUtil.divide(5, -2, 1));
        System.out.println(MathUtil.divide(-5, 2, 2));
        System.out.println(MathUtil.divide(-5, -2, 3));
        System.out.println(MathUtil.divide(355, 113, 2));
        System.out.println(MathUtil.divide(355, 113, 6));
    
    }
}
