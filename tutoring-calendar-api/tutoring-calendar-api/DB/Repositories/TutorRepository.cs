using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using tutoring_calendar_api.Models;

namespace tutoring_calendar_api.DB.Repositories
{
    public class TutorRepository : IRepository<Tutor>
    {
        public List<Tutor> GetAll()
        {
            SqlDataReader rdr = null;
            rdr = DBConnection.ExecuteReader("select * from tutor");

            List<Tutor> tutors = new List<Tutor>();
            while (rdr.Read())
                tutors.Add(new Tutor(rdr.GetInt32(0), rdr.GetString(1), (rdr.IsDBNull(2) ? "" : rdr.GetString(2))));

            rdr.Close();
            return tutors;
        }

        public Tutor GetByID(int id)
        {
            SqlDataReader rdr = null;
            rdr = DBConnection.ExecuteReader("select * from tutor where ra = " + id);

            Tutor tutor = null;
            if (rdr.Read())
                tutor = new Tutor(rdr.GetInt32(0), rdr.GetString(1), (rdr.IsDBNull(2) ? "" : rdr.GetString(2)));

            rdr.Close();
            return tutor;
        }

        public void Insert(Tutor item)
        {
            DBConnection.ExecuteNonQuery("insert into tutor values(" + item.RA + ", '" + item.Name + "', '" + item.Description + "')");
        }

        public void Update(Tutor item)
        {
            DBConnection.ExecuteNonQuery("update tutor set name = '" + item.Name + "', description = '" + item.Description + "' where ra = " + item.RA);
        }

        public void Delete(int id)
        {
            DBConnection.ExecuteNonQuery("delete from tutor where ra = " + id);
        }
    }
}