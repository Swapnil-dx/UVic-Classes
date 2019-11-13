/*
 * The shell of the class, to be completed as part of CSC115 Assignment 3 : Calculator.
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
 * NOTE TO STUDENT:
 * Fill in any of the parts that have the comment:
	 ******COMPLETE *****
 * Do not change method headers or code that has been supplied.
 * All methods must be properly commented.
 * Please delete all messages to you, incuding this one, before submitting.
 */

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
	 * The allowable operators are {+,-,*,/,^}.
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
		
	/*
	 * NOTE TO STUDENT for infixToPostfix below...:
	 * You do not need to check that the infixTokens data field is a legitimate infix
	 * expression at this time.
	 * If, during the process, something unexpected happens, then throw an Exception, but it 
	 * is okay for the postfixTokens to contain an invalid postfix expression.
	 * It is only when processing the public method 'evaluate', that any errors must be
	 * acknowledged.
	 */

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
		System.out.println(infixTokens.toString());
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
						while(!myStack.isEmpty() && !(myStack.pop().equals("("))){
							myStack.pop();
						}
					} else {
						while(!myStack.isEmpty() && checkPrecedence(token) <= checkPrecedence(myStack.peek())){
							postfixTokens.append(myStack.pop());
						}	
					}
					myStack.push(token);
					
				}
			} else {
				postfixTokens.append(token);   
			}
			
		}
		while(!myStack.isEmpty()){
				postfixTokens.append(myStack.pop());
			}
		
		System.out.println(postfixTokens.toString());
	}

	public String getInfixExpression() {
	 //******COMPLETE *****
		return null;
	}

	public String getPostfixExpression() {
	 //******COMPLETE *****
		return null;
	}
		
	public double evaluate() {
	 //******COMPLETE *****
		return -1;
	}
						
	public static void main(String[] args) {
		ArithExpression x= new ArithExpression("6*3+2");
	}
			
}
