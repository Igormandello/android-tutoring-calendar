using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using tutoring_calendar_api.DB.Repositories;
using tutoring_calendar_api.Models;

namespace tutoring_calendar_api.Controllers
{
    [RoutePrefix("api/tutorSchedule")]
    public class TutorScheduleController : ApiController
    {
        public static readonly TutorScheduleRepository repo = new TutorScheduleRepository();

        [Route("")]
        [AcceptVerbs("GET")]
        public List<TutorSchedule> GetAll()
        {
            return repo.GetAll();
        }

        [Route("{key}")]
        [AcceptVerbs("GET")]
        public object GetByRA(int key)
        {
            if (key.ToString().Length == 5)
                return repo.GetByRA(key);
            else
                return repo.GetByID(key);
        }

        [Route("{ra}/{weekday}")]
        [AcceptVerbs("GET")]
        public List<TutorSchedule> GetByDay(int ra, int weekday)
        {
            return repo.GetByRA(ra).Where((t) => (int)t.Weekday == weekday).ToList();
        }

        [Route("")]
        [AcceptVerbs("POST")]
        public HttpResponseMessage Add(TutorSchedule t)
        {
            try
            {
                repo.Insert(t);
            }
            catch
            {
                return new HttpResponseMessage(System.Net.HttpStatusCode.InternalServerError);
            }

            return new HttpResponseMessage(System.Net.HttpStatusCode.OK);
        }

        [Route("{id}")]
        [AcceptVerbs("PUT")]
        public HttpResponseMessage Update(int id, TutorSchedule t)
        {
            try
            {
                t.Id = id;
                repo.Update(t);
            }
            catch
            {
                return new HttpResponseMessage(System.Net.HttpStatusCode.InternalServerError);
            }

            return new HttpResponseMessage(System.Net.HttpStatusCode.OK);
        }

        [Route("{id}")]
        [AcceptVerbs("DELETE")]
        public HttpResponseMessage Delete(int id)
        {
            try
            {
                repo.Delete(id);
            }
            catch
            {
                return new HttpResponseMessage(System.Net.HttpStatusCode.InternalServerError);
            }

            return new HttpResponseMessage(System.Net.HttpStatusCode.OK);
        }
    }
}