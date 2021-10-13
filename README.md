This project is only a small exercise... *wink* *wink*

**Api spec**: src/main/resources/static/openapi.yaml

**Authentication**: If you would have asked me to implement authentication,
I would have just add Spring security with JWT token for a very simple reason.
Taking into consideration the time limit of the assignment, that's the fastest
from-the-shelf authentication which is also industry standard.
If that was a real project, I would consider adding OAuth2 layer, for the
better user experience.

**Redundancy**: I hope I got the question right, and it's about reliability
of the system, considering system faults. I am not too familiar with such requirements, 
but I have some general thoughts. In 2021, it's not that we have to host our own
services, and take power-outage, natural disasters, and system hackers
(who hacks the underlying server, not the application itself), or other external issues into consideration.
Let's just cooperate with an AWS professional, one will know how to utilize
Auto Scaling Group, and other cloud features, that I honestly don't know from the top
of my mind :) If it's about redundancy of code (similar DTOs to model, etc), 
please let me know, and I would be happy to explain my thoughts