using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace tutoring_calendar_api.Models
{
    public class TutorSchedule
    {
        public int Id { get; set; }
        public int TutorRA { get; set; }
        public TimeSpan InitialHour { get; set; }
        public int Duration { get; set; }
        public DayOfWeek Weekday { get; set; }
        public String Place { get; set; }

        public TutorSchedule(int id, int tutorRA, TimeSpan initialHour, int duration, int weekday, string place)
        {
            Id = id;
            TutorRA = tutorRA;
            InitialHour = initialHour;
            Duration = duration;
            Weekday = (DayOfWeek) weekday;
            Place = place;
        }
    }
}