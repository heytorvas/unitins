using System;
using System.Collections.Generic;
using System.Text;

namespace Calculator
{
    class Calculator
    {
        private List<double> _values = new List<double>();

        public List<double> Values
        {
            get { return _values; }
            set { _values = value; }
        }

    }
}
