package amg.technicalevaluation.kracekennedyemployeeapplication.DAO;

public enum DBLibrary {
    CONNECTIONURL{
        @Override
        public String toString() {
            return "jdbc:sqlserver://localhost:1433;databaseName=TechnicalEvalDB;user=sa;password=Antonio_8770972;";
        }
    }
}
