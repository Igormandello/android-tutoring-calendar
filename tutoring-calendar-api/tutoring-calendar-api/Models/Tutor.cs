﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace tutoring_calendar_api.Models
{
    public class Tutor
    {
        public int RA { get; set; }
        public String Name { get; set; }

        public Tutor(int ra, string name)
        {
            RA = ra;
            Name = name;
        }
    }
}