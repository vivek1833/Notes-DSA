#include <bits/stdc++.h>
using namespace std;
stack<int> s;

int main()
{
    string exp;
    cout << "Enter postfix expression: ";
    cin >> exp;

    // traversing postfix expression from left to right
    for (int i = 0; i < exp.length(); i++)
    {

        // if symbol is a digit push it in stack
        if (isdigit(exp[i]))
            s.push(exp[i] - '0');

        // if symbol is an operator then pop top 2 elements from stack
        else
        {
            int op2 = s.top();
            s.pop();
            int op1 = s.top();
            s.pop();

            if (exp[i] == '+')
                s.push(op1 + op2);
            else if (exp[i] == '-')
                s.push(op1 - op2);
            else if (exp[i] == '*')
                s.push(op1 * op2);
            else if (exp[i] == '/')
                s.push(op1 / op2);
        }
    }
    cout << "After evalution we get: " << s.top();

    return 0;
}