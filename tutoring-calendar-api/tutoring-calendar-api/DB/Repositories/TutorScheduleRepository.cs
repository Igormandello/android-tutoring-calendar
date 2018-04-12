using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using tutoring_calendar_api.Models;

namespace tutoring_calendar_api.DB.Repositories
{
    public class TutorScheduleRepository : IRepository<TutorSchedule>
    {
        public List<TutorSchedule> GetAll()
        {
            SqlDataReader rdr = null;
            rdr = DBConnection.ExecuteReader("select * from tutor_schedule");

            List<TutorSchedule> schedules = new List<TutorSchedule>();
            while (rdr.Read())
                schedules.Add(new TutorSchedule(rdr.GetInt32(0), rdr.GetInt32(1), rdr.GetTimeSpan(2), rdr.GetInt32(3), rdr.GetInt32(4), rdr.GetString(5)));

            rdr.Close();
            return schedules;
        }

        public List<TutorSchedule> GetByRA(int ra)
        {
            SqlDataReader rdr = null;
            rdr = DBConnection.ExecuteReader("select * from tutor_schedule where tutor_ra = " + ra);

            List<TutorSchedule> schedules = new List<TutorSchedule>();
            while (rdr.Read())
                schedules.Add(new TutorSchedule(rdr.GetInt32(0), rdr.GetInt32(1), rdr.GetTimeSpan(2), rdr.GetInt32(3), rdr.GetInt32(4), rdr.GetString(5)));

            rdr.Close();
            return schedules;
        }

        public TutorSchedule GetByID(int id)
        {
            SqlDataReader rdr = null;
            rdr = DBConnection.ExecuteReader("select * from tutor_schedule where id = " + id);

            TutorSchedule schedule = null;
            if (rdr.Read())
                schedule = new TutorSchedule(rdr.GetInt32(0), rdr.GetInt32(1), rdr.GetTimeSpan(2), rdr.GetInt32(3), rdr.GetInt32(4), rdr.GetString(5));

            rdr.Close();
            return schedule;
        }

        public void Insert(TutorSchedule item)
        {
            DBConnection.ExecuteNonQuery("insert into tutor_schedule values(" + item.TutorRA + ", '" +
                                         item.InitialHour + "', " +
                                         item.Duration + ", " +
                                         (int)item.Weekday + ", '" +
                                         item.Place + "')");
        }

        public void Update(TutorSchedule item)
        {
            DBConnection.ExecuteNonQuery("update tutor_schedule set " +
                                         "tutor_ra = " + item.TutorRA + ", " +
                                         "initial_hour = '" + item.InitialHour + "', " +
                                         "duration = " + item.Duration + ", " +
                                         "weekday = " + (int)item.Weekday + ", " +
                                         "place = '" + item.Place + "' " +
                                         "where ra = " + item.Id);
        }

        public void Delete(int id)
        {
            DBConnection.ExecuteNonQuery("delete from tutor_schedule where id = " + id);
        }
    }
}