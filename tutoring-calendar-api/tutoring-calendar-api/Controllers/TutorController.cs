using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Globalization;
using System.IO;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Reflection;
using System.Resources;
using System.Web.Http;
using tutoring_calendar_api.DB.Repositories;
using tutoring_calendar_api.Models;

namespace tutoring_calendar_api.Controllers
{
    [RoutePrefix("api/tutor")]
    public class TutorController : ApiController
    {
        public static readonly TutorRepository repo = new TutorRepository();

        [Route("")]
        [AcceptVerbs("GET")]
        public List<Tutor> GetAll()
        {
            return repo.GetAll();
        }

        [Route("{ra}")]
        [AcceptVerbs("GET")]
        public Tutor GetByRA(int ra)
        {
            return repo.GetByID(ra);
        }

        [Route("{ra}/image")]
        [AcceptVerbs("GET")]
        public String GetImage(int ra)
        {
            Image i = (Image)Properties.Resources.ResourceManager.GetObject(ra + "");
            if (i == null)
                i = Properties.Resources.no_image;

            MemoryStream ms = new MemoryStream();
            i.Save(ms, i.RawFormat);

            byte[] array = ms.ToArray();
            return Convert.ToBase64String(array);
        }

        [Route("")]
        [AcceptVerbs("POST")]
        public HttpResponseMessage Add(Tutor t)
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

        [Route("{ra}")]
        [AcceptVerbs("PUT")]
        public HttpResponseMessage Update(int ra, Tutor t)
        {
            try
            {
                t.RA = ra;
                repo.Update(t);
            }
            catch
            {
                return new HttpResponseMessage(System.Net.HttpStatusCode.InternalServerError);
            }

            return new HttpResponseMessage(System.Net.HttpStatusCode.OK);
        }

        [Route("{ra}")]
        [AcceptVerbs("DELETE")]
        public HttpResponseMessage Delete(int ra)
        {
            try
            {
                repo.Delete(ra);
            }
            catch
            {
                return new HttpResponseMessage(System.Net.HttpStatusCode.InternalServerError);
            }

            return new HttpResponseMessage(System.Net.HttpStatusCode.OK);
        }
    }
}