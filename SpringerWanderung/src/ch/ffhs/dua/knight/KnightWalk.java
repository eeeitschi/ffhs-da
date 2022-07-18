package ch.ffhs.dua.knight;

public class KnightWalk
{
    /**
     * Returns a knight-walk on a chess-board with given number of rows an columns.
     * Everey field of the chess-board must be visited exactly once.
     * @param rows
     * @param columns
     * @param start The start field
     * @return
     */
    public static Field[] walk(int rows, int columns, Field start)
    {
        // TODO
        return null;
    }

    public static class Field
    {
        final int row;
        final int col;

        public Field(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Field field = (Field) o;

            if (row != field.row) return false;
            return col == field.col;
        }

        @Override
        public int hashCode()
        {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }
}
