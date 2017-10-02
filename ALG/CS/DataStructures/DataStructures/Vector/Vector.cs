using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataStructures.Vector
{
    public class Vector<T>
    {
        private T[] data;

        private int size;

        public int Size { get { return size; } }

        public int Length { get { return data.Length; } }

        public Vector(int Size)
        {
            data = new T[Size];
        }

        public Vector() : this(16) { }

        public void Add(T item)
        {
            // Ensure space
            EnsureSpace();
            // Insert the object
            data[size] = item;
            // Increment the 'size'
            size++;
        }

        public void Add(T item, int position)
        {
            EnsureSpace();
            if (position > size)
                VerifyPosition(position);
            for(int i = size; i >= position; i -= 1)
            {
                data[i + 1] = data[i];
            }
            size++;
            data[position] = item;
        }

        public void AddRange(T[] items)
        {
            foreach (T item in items)
            {
                Add(item);
            }
        }

        public T Get(int position)
        {
            VerifyPosition(position);
            return data[position];
        }

        public T Remove(int position)
        {
            VerifyPosition(position);
            T removed = data[position];
            data[position] = default(T);
            for (int i = position; i < size - 1; i++)
            {
                data[i] = data[i + 1];
            }
            data[size - 1] = default(T);
            size--;
            return removed;
        }

        public bool Contains(T item)
        {
            foreach (T _item in data)
            {
                if (item.Equals(_item))
                    return true;
            }
            return false;
        }

        public void PrintData()
        {
            for (int i = 0; i < Size; i++)
            {
                Console.WriteLine(String.Format("[{0}:{1}]", i, Get(i)));
            }
        }

        private void EnsureSpace()
        {
            // Verify if the resize is necessary
            if (size == data.Length)
            {
                // Do the resize operation
                T[] fresh = new T[data.Length * 2];
                CopyContent(data, fresh);
                data = fresh;
            }
        }

        private void CopyContent(T[] old, T[] fresh)
        {
            for (int i = 0; i < old.Length; i++)
            {
                fresh[i] = old[i];
            }
        }

        private void VerifyPosition(int position)
        {
            if (position >= size)
            {
                throw new IndexOutOfRangeException(String.Format("Index {0} out of range.", position));
            }
        }
    }
}
