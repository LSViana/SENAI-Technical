using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SStorage.Utils
{
    public static class Objects
    {

        public static void Copy<T>(this T destiny, T origin, IEnumerable<string> fieldsToIgnore)
        {
            fieldsToIgnore = fieldsToIgnore.ToArray();
            //
            var typeDestiny = destiny.GetType();
            var propsDestiny = typeDestiny.GetProperties();
            var typeOrigin = origin.GetType();
            var propsOrigin = typeOrigin.GetProperties();
            foreach (var prop in propsDestiny)
            {
                if (prop.SetMethod == null)
                    continue;
                var propOrigin = propsOrigin.FirstOrDefault(a => a.Name == prop.Name);
                if (propOrigin is null)
                    continue;
                if (fieldsToIgnore.Contains(prop.Name))
                    continue;
                prop.SetValue(destiny, prop.GetValue(origin));
            }
        }

    }
}
