
/*This is an implementation of an emergency waiting room in an hospital 
where patients are served on first come first serve basis.
Implemented using queues.*/




    #include<iostream>
    #include<string>
    #include<stdio.h>
    #include <queue>

    using namespace std;

    class Patient{ //patient class to store information about patients

    public:
    int id;
    string name;
    };

    class Waiting_Room{  //wating room class has functions to manage the pateints in the room

    public:

    static queue <Patient> Patients; 
    static int id_gen;

    void RegisterPatient(string);
    void ServePatient();
    void CancelAll();
    bool CanDoctorGoHome();
    void ShowAllPatient();

    };

    int Waiting_Room:: id_gen=1;
    queue<Patient> Waiting_Room::Patients;


    void Waiting_Room:: RegisterPatient(string _name){ //creatd patient objects and stores them in the patients queue
    Patient newPatient;
    newPatient.name=_name;
    newPatient.id=id_gen++;
    Patients.push(newPatient);
    cout<<"Patient "<<newPatient.name<<" with ID: "<<newPatient.id<<" is Registered.\n";

    }

    void Waiting_Room:: ServePatient(){ //serves patients on the basis first come first serve
        Patient temp=Patients.front();
        cout<<"Patient "<<temp.name<<" with ID: "<<temp.id<<" is served.\n";
        Patients.pop();
    }
    void Waiting_Room::CancelAll(){  //cancels all appointments
        while(!Patients.empty()){
            Patients.pop();//pops until empty
        }
        cout<<"\nAll apointments cancelled\n\n";
    }

    bool Waiting_Room::CanDoctorGoHome(){ //doctor goes home  if all patients served
        if(Patients.empty())
        {
            cout<<"All patients served. Doctor can go home.\n\n";
            return true;
        }
        else{
            cout<<"Patients Remaining. Doctor cannot go home.\n\n";
            return false;
        }
    }
        void Waiting_Room::ShowAllPatient(){ //sorts patient records in alphabetical order
        vector<Patient> tempRecord;
        while(!Patients.empty()){
            tempRecord.push_back(Patients.front());
            Patients.pop();
        }
    //sort by alphabetical order
        for (int i=0;i<tempRecord.size();i++){
            for(int j=0;j<tempRecord.size();j++){
                if (tempRecord[i].name<tempRecord[j].name){
                    Patient temp;
                    temp=tempRecord[j];
                    tempRecord[j]=tempRecord[i];
                    tempRecord[i]=temp;
                }
            }
        }

            // Display the sorted patient records
        cout<<"\nSorted Rcords:\n\n";
        for (int i = 0; i < tempRecord.size(); i++) {
            cout << "Patient ID: " << tempRecord[i].id << ", Name: " << tempRecord[i].name << endl;
        }
        cout<<"\n\n";
        //sort by ids to go back to original queue
            for (int i=0;i<tempRecord.size();i++){
            for(int j=0;j<tempRecord.size();j++){
                if (tempRecord[i].id<tempRecord[j].id){
                    Patient temp;
                    temp=tempRecord[j];
                    tempRecord[j]=tempRecord[i];
                    tempRecord[i]=temp;
                }
            }
        }
    
        //pushing it back into the Queue
    
        for (int i = 0; i < tempRecord.size(); i++) {
            Patients.push(tempRecord[i]);
        }
    
    }


    int main(){
        Waiting_Room r1;
        r1.RegisterPatient("Ahmed");
        r1.RegisterPatient("salman");
        r1.RegisterPatient("basim");
        r1.ShowAllPatient();
        r1.ServePatient();
        r1.CanDoctorGoHome();
        r1.CancelAll();
        r1.CanDoctorGoHome();
       /* r1.ServePatient();
   
    r1.ServePatient();
    
   */

    }
