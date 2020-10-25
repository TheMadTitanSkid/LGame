﻿using java.lang;
using System;
using System.Collections.Generic;
using System.Text;

namespace java.io
{
    public class PrintStream
    {
        private readonly bool iserr;
        private readonly System.Text.StringBuilder line;

        public delegate void LineConsumer(string line);
        private LineConsumer redirection;

        public PrintStream(bool iserr) : base()
        {
            this.iserr = iserr;
            this.line = new System.Text.StringBuilder();
            this.redirection = null;
        }
        private void FinishLine()       // must be called with a lock on 'line'
        {
            string l = line.ToString();
            line.Clear();
            if (redirection != null)
            {
                redirection(l);
            }
            else if (iserr)
            {
                System.Console.Error.WriteLine(l);
            }
            else
            {
                System.Console.WriteLine(l);
            }
        }

        // public interface is thread-safe

        public void Print(bool b)
        {
            lock (line)
            {
                line.Append(JavaSystem.Str(b));
            }
        }

        public void Print(double d)
        {
            lock (line)
            {
                line.Append(JavaSystem.Str(d));
            }
        }

        public void Print(char c)
        {
            lock (line)
            {
                line.Append(c);
            }
        }

        public void Print(int i)
        {
            lock (line)
            {
                line.Append(i);
            }
        }

        public void Print(object o)
        {
            lock (line)
            {
                line.Append(JavaSystem.Str(o));
            }
        }

        public void Println()
        {
            lock (line)
            {
                FinishLine();
            }
        }

        public void Println(bool b)
        {
            lock (line)
            {
                line.Append(JavaSystem.Str(b));
                FinishLine();
            }
        }

        public void Println(double d)
        {
            lock (line)
            {
                line.Append(JavaSystem.Str(d));
                FinishLine();
            }
        }

        public void Println(char c)
        {
            lock (line)
            {
                line.Append(c);
                FinishLine();
            }
        }

        public void Println(int i)
        {
            lock (line)
            {
                line.Append(i);
                FinishLine();
            }
        }

        public void Println(object o)
        {
            lock (line)
            {
                line.Append(JavaSystem.Str(o));
                FinishLine();
            }
        }

        // installation hook to re-direct the output to some
        // arbitrary location    
        public void Redirect(LineConsumer r)
        {
            lock (line)
            {
                this.redirection = r;
            }
        }
    }
}