/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 6/12/2016
* Filename: ArithExpression.java
* Details: CSC115 Assignment 3
*/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArithExpression {

	private TokenList postfixTokens;
	private TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 	An invalid expression is not expressly checked for, but will not be
	 * 	successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is 
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 || 	
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^,(,)}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
			case "(":
			case ")":
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * A private method that returns a value between 0-3 depending on
	 * the precedence of an operator passed into the method
	 * @param item The operator in question
	 * @return int Precedence value between 0-3
	 */
	private static int checkPrecedence (String item) {
		
		if(item.equals("+") || item.equals("-")){
			return 1;
		} else if(item.equals("*") || item.equals("/")){
			return 2;
		} else if(item.equals("^")){
			return 3;
		} else{
			return 0;
		}
	}

	 /**
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
	 * If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	private void infixToPostfix() {
		postfixTokens=  new TokenList();
		StringStack myStack= new StringStack();
		for(int i=0; i<infixTokens.size(); i++){
			String token= infixTokens.get(i);
			
			if(this.isOperator(token)){
				if(myStack.isEmpty() || checkPrecedence(token)>checkPrecedence(myStack.peek())){
					myStack.push(token);
				} else {
					if(token.equals("(")){
						myStack.push(token);
					} else
					if(token.equals(")")){
						while(!myStack.isEmpty() && !(myStack.peek().equals("("))){
							postfixTokens.append(myStack.pop());
						}
						if(myStack.peek().equals("(")){
							myStack.pop();
						}
					} else {
						while(!myStack.isEmpty() && checkPrecedence(token) <= checkPrecedence(myStack.peek())){
							postfixTokens.append(myStack.pop());
						}
						myStack.push(token);
					}
					
					
				}
			} else {
				postfixTokens.append(token);   
			}
			
		}
		while(!myStack.isEmpty()){
				postfixTokens.append(myStack.pop());
			}
	}

	/* Returns a string with the infix expression. 
	 */
	public String getInfixExpression() {
		return infixTokens.toString();
	}
	
	/* Returns a string with the postfix expression. 
	 */
	public String getPostfixExpression() {
		return postfixTokens.toString();
	}
	
	/*  
	* This method evaluates a postfix expression and returns the final answer as a double
	* @return double finalAnswer The final answer of the evaluated expression
	* @throws StackEmptyException if the number of operators is not correct.
	*/
	public double evaluate() {
		StringStack myStack= new StringStack();
		double finalAnswer= 0.0;
		for(int i= 0; i<postfixTokens.size(); i++){
			String temp= postfixTokens.get(i);
			if(this.isOperator(temp)){
					double num1= 0;
					double num2= 0;
					try{
						num1 = Double.parseDouble(myStack.pop());
						num2 = Double.parseDouble(myStack.pop());
					} catch (StackEmptyException e){
						throw new StackEmptyException("Expression cannot be evaluated");
					}
					switch(temp) {
						case "+":
							finalAnswer= num1 + num2;
							break;
						case "-":
							finalAnswer= num2 - num1;
							break;
						case "/":
							finalAnswer= num2 / num1;
							break;
						case "*":
							finalAnswer= num1 * num2;
							break;
						case "^":
							finalAnswer= Math.pow(num2, num1);
							break;
					}
					myStack.push(Double.toString(finalAnswer));
			} else {
				myStack.push(temp);
			}
		}
		finalAnswer = Double.parseDouble(myStack.pop());
		return finalAnswer;
	}
						
	public static void main(String[] args) {
		ArithExpression x= new ArithExpression("5*(3+2)-2/2");
		System.out.println(x.getInfixExpression());
		System.out.println(x.getPostfixExpression());
		System.out.println(x.evaluate());
		
		System.out.println();
		
		ArithExpression y= new ArithExpression("3++2");  //to check if it catches error
		System.out.println(y.getInfixExpression());
		System.out.println(y.getPostfixExpression());
		System.out.println(y.evaluate());
	}
			
}