import sqlite3
import os

migration_dir = os.path.dirname(os.path.realpath(__file__))
qry = open(migration_dir + '/migration.sql', 'r').read()

# Create and connect to the database
conn = sqlite3.connect('plumitive.db')
print("Created local plumitive database")

#Create cursor
c = conn.cursor()

# Create table
c.execute(qry)
print("Created documents table")

# Save (commit) the changes
conn.commit()

# Close cursor and connection
c.close()
conn.close()
print("Closed the database connection")
