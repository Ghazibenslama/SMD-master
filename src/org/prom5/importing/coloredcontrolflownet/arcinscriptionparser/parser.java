
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Fri Dec 21 23:35:38 CET 2007
//----------------------------------------------------

package org.prom5.importing.coloredcontrolflownet.arcinscriptionparser;

import java.util.ArrayList;
import java.util.List;

import org.prom5.framework.models.coloredcontrolflownet.expression.BooleanExpression;
import org.prom5.framework.models.coloredcontrolflownet.expression.Expression;
import org.prom5.framework.models.coloredcontrolflownet.expression.FunctionCallExpression;
import org.prom5.framework.models.coloredcontrolflownet.expression.IdentifierExpression;
import org.prom5.framework.models.coloredcontrolflownet.expression.IntegerExpression;
import org.prom5.framework.models.coloredcontrolflownet.expression.StringExpression;
import org.prom5.framework.models.coloredcontrolflownet.expression.ValueExpression;
import org.prom5.framework.models.coloredcontrolflownet.inscription.ArcInscription;
import org.prom5.framework.models.coloredcontrolflownet.inscription.InitialConfigurationInscription;
import org.prom5.framework.models.coloredcontrolflownet.inscription.RouteInscription;
import org.prom5.framework.models.coloredcontrolflownet.inscription.StatementInscription;
import org.prom5.framework.models.coloredcontrolflownet.inscription.TakeStateInscription;
import org.prom5.framework.models.coloredcontrolflownet.statement.AssignStatement;
import org.prom5.framework.models.coloredcontrolflownet.statement.NoOperationStatement;
import org.prom5.framework.models.coloredcontrolflownet.statement.SequenceStatement;
import org.prom5.framework.models.coloredcontrolflownet.statement.Statement;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Fri Dec 21 23:35:38 CET 2007
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\023\000\002\002\003\000\002\002\004\000\002\002" +
    "\007\000\002\002\011\000\002\003\003\000\002\003\012" +
    "\000\002\004\010\000\002\004\004\000\002\004\002\000" +
    "\002\005\003\000\002\005\004\000\002\005\004\000\002" +
    "\005\004\000\002\005\004\000\002\005\006\000\002\005" +
    "\010\000\002\006\004\000\002\006\004\000\002\006\002" +
    "" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\006\004\005\007\006\001\002\000\004\002" +
    "\066\001\002\000\004\007\007\001\002\000\004\002\001" +
    "\001\002\000\004\010\010\001\002\000\010\006\012\023" +
    "\014\024\013\001\002\000\004\005\065\001\002\000\004" +
    "\005\ufffd\001\002\000\010\004\054\010\052\025\ufff9\001" +
    "\002\000\004\004\015\001\002\000\004\013\016\001\002" +
    "\000\004\010\017\001\002\000\016\011\025\015\020\017" +
    "\021\020\023\021\022\022\024\001\002\000\004\004\043" +
    "\001\002\000\004\004\036\001\002\000\004\014\035\001" +
    "\002\000\004\016\034\001\002\000\006\012\033\013\032" +
    "\001\002\000\022\005\ufff8\010\ufff8\011\ufff8\015\ufff8\017" +
    "\ufff8\020\ufff8\021\ufff8\022\ufff8\001\002\000\004\010\027" +
    "\001\002\000\006\006\012\023\014\001\002\000\004\005" +
    "\031\001\002\000\004\005\ufffc\001\002\000\022\005\ufff6" +
    "\010\ufff6\011\ufff6\015\ufff6\017\ufff6\020\ufff6\021\ufff6\022" +
    "\ufff6\001\002\000\022\005\ufff7\010\ufff7\011\ufff7\015\ufff7" +
    "\017\ufff7\020\ufff7\021\ufff7\022\ufff7\001\002\000\022\005" +
    "\ufff5\010\ufff5\011\ufff5\015\ufff5\017\ufff5\020\ufff5\021\ufff5" +
    "\022\ufff5\001\002\000\022\005\ufff4\010\ufff4\011\ufff4\015" +
    "\ufff4\017\ufff4\020\ufff4\021\ufff4\022\ufff4\001\002\000\004" +
    "\013\037\001\002\000\004\010\040\001\002\000\004\006" +
    "\041\001\002\000\004\005\042\001\002\000\022\005\ufff2" +
    "\010\ufff2\011\ufff2\015\ufff2\017\ufff2\020\ufff2\021\ufff2\022" +
    "\ufff2\001\002\000\022\005\uffef\010\045\011\025\015\020" +
    "\017\021\020\023\021\022\022\024\001\002\000\022\005" +
    "\uffef\010\045\011\025\015\020\017\021\020\023\021\022" +
    "\022\024\001\002\000\022\005\uffef\010\045\011\025\015" +
    "\020\017\021\020\023\021\022\022\024\001\002\000\004" +
    "\005\047\001\002\000\022\005\ufff3\010\ufff3\011\ufff3\015" +
    "\ufff3\017\ufff3\020\ufff3\021\ufff3\022\ufff3\001\002\000\004" +
    "\005\ufff0\001\002\000\004\005\ufff1\001\002\000\010\004" +
    "\054\010\052\025\ufff9\001\002\000\004\025\062\001\002" +
    "\000\004\013\055\001\002\000\004\010\056\001\002\000" +
    "\016\011\025\015\020\017\021\020\023\021\022\022\024" +
    "\001\002\000\004\005\060\001\002\000\010\004\054\010" +
    "\052\025\ufff9\001\002\000\004\025\ufffb\001\002\000\004" +
    "\005\063\001\002\000\004\002\ufffe\001\002\000\004\025" +
    "\ufffa\001\002\000\004\002\uffff\001\002\000\004\002\000" +
    "\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\004\002\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\003\010\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\004\052\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\005\025\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\003\027\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\005" +
    "\043\006\045\001\001\000\006\005\043\006\050\001\001" +
    "\000\006\005\043\006\047\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\004\063\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\005\056\001\001\000\002\001\001" +
    "\000\004\004\060\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    public void report_error(String message, Object info) { /* Ignore simple error */
    }
   
    public void report_fatal_error(String message, Object info) throws Exception {
        StringBuffer m = new StringBuffer("Error");
   
        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
   
            if (s.left >= 0) {                
                m.append(" in line "+(s.left+1));   
                if (s.right >= 0)                    
                    m.append(", column "+(s.right+1));
            }
        }
   
        m.append(" : "+message);

	  throw new Exception(m.toString());
    }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {


	public String sid(Object o) {
    	String s = (String)o;
    	return s.substring(1,s.length()-1);
    }
    public Statement appendStatement(Statement s1, Statement s2) {
    	if (s1 instanceof NoOperationStatement) return s2;
    	return new SequenceStatement(s1,s2);
    }

  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // exp_list ::= 
            {
              List<Expression> RESULT =null;
		 RESULT = new ArrayList<Expression>();; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp_list",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // exp_list ::= COMMA exp_list 
            {
              List<Expression> RESULT =null;
		int elleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int elright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		List<Expression> el = (List<Expression>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = el; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp_list",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // exp_list ::= expression exp_list 
            {
              List<Expression> RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int elleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int elright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		List<Expression> el = (List<Expression>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 el.add(e); RESULT = el; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp_list",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // expression ::= GET LPAREN STRINGID COMMA CONFS RPAREN 
            {
              Expression RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		 RESULT = new IdentifierExpression(sid(id)); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // expression ::= ID LPAREN exp_list RPAREN 
            {
              Expression RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int elleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int elright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		List<Expression> el = (List<Expression>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = new FunctionCallExpression((String)id, el); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // expression ::= BOOLTYPE BOOL 
            {
              Expression RESULT =null;
		int boolleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int boolright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object bool = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new BooleanExpression(Boolean.parseBoolean((String)bool)); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // expression ::= INTTYPE INT 
            {
              Expression RESULT =null;
		int integerleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int integerright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object integer = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new IntegerExpression(Integer.parseInt((String)integer)); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // expression ::= STRINGTYPE STRINGID 
            {
              Expression RESULT =null;
		int stringleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int stringright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object string = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new StringExpression(sid(string)); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // expression ::= STRINGTYPE STRING 
            {
              Expression RESULT =null;
		int stringleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int stringright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object string = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new StringExpression(sid(string)); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // expression ::= VALUE 
            {
              Expression RESULT =null;
		 RESULT = new ValueExpression(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // assign_list ::= 
            {
              List<AssignStatement> RESULT =null;
		 RESULT = new ArrayList<AssignStatement>(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("assign_list",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // assign_list ::= COMMA assign_list 
            {
              List<AssignStatement> RESULT =null;
		int alleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int alright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		List<AssignStatement> al = (List<AssignStatement>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = al; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("assign_list",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // assign_list ::= LPAREN STRINGID COMMA expression RPAREN assign_list 
            {
              List<AssignStatement> RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int alleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int alright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		List<AssignStatement> al = (List<AssignStatement>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 al.add(new AssignStatement(sid(id), e)); RESULT = al; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("assign_list",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // statement ::= ASSIGN LPAREN STRINGID COMMA expression COMMA statement RPAREN 
            {
              Statement RESULT =null;
		int varleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).left;
		int varright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).right;
		Object var = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-5)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Statement s = (Statement)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = appendStatement(s, new AssignStatement(sid(var), e)); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-7)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // statement ::= CONFS 
            {
              Statement RESULT =null;
		 RESULT = new NoOperationStatement(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // inscription ::= LPAREN PID COMMA LBRACKET assign_list RBRACKET RPAREN 
            {
              ArcInscription RESULT =null;
		int alleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int alright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		List<AssignStatement> al = (List<AssignStatement>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		 RESULT = new InitialConfigurationInscription(al); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("inscription",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // inscription ::= LPAREN PID COMMA statement RPAREN 
            {
              ArcInscription RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Statement s = (Statement)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 if (s instanceof NoOperationStatement) RESULT = new TakeStateInscription();
   				      else RESULT = new StatementInscription(s); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("inscription",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= inscription EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		ArcInscription start_val = (ArcInscription)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // inscription ::= PID 
            {
              ArcInscription RESULT =null;
		 RESULT = new RouteInscription(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("inscription",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

