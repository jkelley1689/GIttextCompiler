# GIttextCompiler
• DOCUMENT \BEGIN ... \END
The document annotations denote the beginning and ending of a valid source file in our Gittex Markdown language. All valid source files must start with \BEGIN and end with \END (i.e., there cannot be any text before or after). Between theses annotations, all other annotations (or none at all, other than the title annotation) may occur except for a repeating of the \BEGIN...\END annotations. You may assume that these annotations occur on their own lines (i.e., no other annotations on the same line) and that there is a newline character (i.e., “\n”) after both \BEGIN and \END annotations. In HTML5, these annotations correspond with the <html> and </html> tags, respectively.

• TITLE \TITLE[text]
The title annotation denotes the title of the resulting html page that shows up in the browser tab. Within the square brackets of this annotation, only plain text is possible (i.e., no other annotations). Title annotations are required for any valid Gittex file. You may assume that these annotations occur on their own lines (i.e., no other annotations on the same line) and that there is a newline character (i.e., “\n”) after the \TITLE[text]. In HTML5, this annotation must be enclosed in <head> ... </head> tags and correspond with the <title> and </title> tags, respectively.

• HEADINGS # text
The heading annotation denotes a first-level heading of the resulting html page. Heading annotations are followed by a space and then text. Within the text of this annotation, only plain text is possible (i.e., no other annotations). You may assume that this annotation occurs on its own line (i.e., no other annotations on the same line), that there is a newline character (i.e., “\n”) after the text annotation. Heading annotations may not occur inside any other annotation other than the \BEGIN ... \END document annotation required in any Gittex file and may not contain any other annotations (e.g., bold, italics, etc.). In HTML5, these annotations correspond to <h1> ... </h1>.

• PARAGRAPH \PARB ... \PARE
The \PARB ... \PARE (i.e., paragraph begin and paragraph end) annotation denotes a paragraph within the Gittex source file. You may assume that this annotation occurs on its own line (i.e., no other annotations on the same line), that there is a newline character (i.e., “\n”) after the \PARB and \PARE annotation. Following the \PARB annotation can either be a variable definition (see below) and/or the text of the paragraph. The paragraph text is terminated by \PARE. Within a paragraph, only the bold, italics and link annotations are allowed, but not required (note that you cannot have a paragraph annotation within another paragraph annotation). In HTML5, this annotation corresponds with the <p> and </p> tags, respectively.
     
• BOLD *text*
The bold annotation, signaled by one asterisk and a space before/after plain text, denotes the beginning and ending of text within the Gittex source file that is in a bold font. Within these annotations, only plain text is possible (i.e., no other annotations). Bold annotations do not have to occur within paragraph annotations, they may occur on their own where text is allowed other than title, heading, image, link and italics tags. In HTML5, these annotations correspond with the <b> and </b> tags, respectively.

• UNORDERED LISTS + list item
The unordered list annotation denotes a bulleted list item within the Gittex source file. A list item, which must start with a “+”, be followed by a space and end with a newline (i.e., “\n”) character may contain only the bold, italics and link annotations but not required (i.e., it can just be plain text). In HTML5, these annotations correspond with the <li> and </li>tags, respectively.

• NEWLINE \\
The new line annotation, within the Gittex source file, may appear anywhere within a document outside of the title annotations. In HTML5, this annotation corresponds with the <br> tag.

• LINKS [text](address)
The link annotation, within the Gittex source file, denotes a link element (see http://www.w3schools.com/html/html_links.asp). The [] and () annotations must contain some text (denoted by text and address above) giving the address of the page to link to. 
For this annotation, you do not need to validate the address – you may assume whatever address
provided is valid.

• IMAGES ![text](address)
The image annotation denotes an image element (see http://www.w3schools.com/tags/tag_img.asp). The ![] and () annotations must contain some text (denoted by text and address above) giving the address of the image.
For this annotation, you do not need to validate the address – you may assume whatever address provided is a valid image address.
   
In addition to these annotations, our Gittex Markdown language will include the capability to define and use statically-scoped variables, defined as follows:

• VARIABLE DEFINITIONS \DEF[variable name = value]
The define annotations structure denotes the beginning and ending of a variable definition within the Gittex source file. The \DEF[] annotations must contain some text (denoted by variable name above) giving the name of the variable, an “=” annotation that must be followed by some text (denoted by value above) giving the value of the variable. The \DEF annotation may occur within the \BEGIN ... \END and \PARB ...\PARE annotation blocks but, if it occurs, it must be the very first annotation to occur within that block (i.e., immediately following the start of another annotation). The scope of the variable definition starts after the \DEF[] tag in the block and continues to the end of its immediate enclosing block.

• VARIABLE USAGE \USE[variable name]
The use annotations denotes the use of a variable within the Gittex source file. The \USE[] annotations must contain only text (denoted by variable name above) noting the variable value to use. The #\USE[] annotation may occur within any other annotation block except title, links and images.
Note that all annotations are not case sensitive (i.e., \BEGIN and \begin are equivalent and legal).

Finally, in our Gittex Markdown documents, you may assume that whenever there is text (both in text and the cases when an address is provided) possible the following are the only allowed characters:

• Upper and lower-case letters: A .. Z; a .. z

• Numbers: 0 .. 9

• Punctuation: commas (i.e., ‘,’), periods (i.e., ‘.’), quotes (i.e., ‘”’), colons (i.e., ‘:’), question
marks (i.e., ‘?’), underscore (i.e., ‘_’) and forward slashes (i.e., ‘/’)

• Special characters: newline, tabs

Except for these characters, you may assume no other character is possible in the text and your grammar does not need to account for them (i.e., the “#”, “!”, “*“, etc. characters will only be used to denote one of our Gittex annotations and will not be found in the text).
