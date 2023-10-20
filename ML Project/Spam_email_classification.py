import nltk
import random
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import accuracy_score, classification_report

# Download NLTK's stop words
nltk.download('stopwords')
nltk.download('punkt')

# Load the email dataset (you can replace this with your own dataset)
# In this example, we'll create a small dataset with spam and non-spam emails.
spam_data = [
    ("Buy cheap luxury watches now!", "spam"),
    ("Hi, how about lunch later?", "non-spam"),
    ("Congratulations, you've won a prize!", "spam"),
    ("Meeting for coffee tomorrow?", "non-spam"),
]

# Shuffle the data for randomness
random.shuffle(spam_data)

# Extract features and labels
emails, labels = zip(*spam_data)

# Preprocess the text data
stop_words = set(stopwords.words('english'))
vectorizer = CountVectorizer(stop_words='english')
X = vectorizer.fit_transform(emails)
y = [1 if label == "spam" else 0 for label in labels]

# Split the data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train a Naive Bayes classifier
classifier = MultinomialNB()
classifier.fit(X_train, y_train)

# Make predictions
y_pred = classifier.predict(X_test)

# Calculate accuracy and print the classification report
accuracy = accuracy_score(y_test, y_pred)

# Specify labels explicitly
report = classification_report(y_test, y_pred, labels=[0, 1], target_names=["non-spam", "spam"])

print(f"Accuracy: {accuracy}")
print(report)

text="meeting at 5pm"
k=[]
k.append(text)
k_vector = vectorizer.transform(k)

if(classifier.predict(k_vector)==1):
      print("Spam")
else:
      print("not spam")