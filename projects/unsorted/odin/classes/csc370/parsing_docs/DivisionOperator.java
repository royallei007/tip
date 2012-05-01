/**
 * This represents the operator / in an arithmetic equation; as in 6 / 2 = 3.
 *
 * @author Will Holcomb
 */public class DivisionOperator extends Operator
{
	/**
	 * Creates an {@link Operator} with:
	 * <ul>
	 *	<li>token: /</li>
	 *	<li> precedence: 2</li>
	 *	<li>numberOperands: 2</li>
	 *	<li>preceededByAnOperand: true</li>
	 * </ul>
	 */
	public DivisionOperator()
	{
		token = new String("/");
		precedence = 2;
		numberOperands = 2;
		preceededByAnOperand = true;
	}

	/**
	 * @param x[] the operands
	 * @return x[0] / x[1]
	 */
	public int operate(int [] x)
	{
		int solution;

		if(x.length == numberOperands)
				solution = x[0] / x[1];
		else
		{
			System.out.println("Error in \"" + token + "\"");
			solution = 0;
		}

		return solution;
	}
}	