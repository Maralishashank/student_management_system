const API = "http://localhost:8080/api/students";

async function fetchList(){
  try{
    const res = await fetch(API);
    const data = await res.json();
    const list = document.getElementById('list');
    list.innerHTML = '';
    data.forEach(s => {
      const el = document.createElement('div');
      el.innerHTML = `<strong>${s.firstName} ${s.lastName || ''}</strong> - ${s.rollNo || ''} - ${s.email || ''} 
        <button onclick="del(${s.id})">Delete</button>`;
      list.appendChild(el);
    });
  }catch(e){
    // backend not running; show placeholder
    document.getElementById('list').innerHTML = '<em>Start backend to list students (or ignore if uploading only).</em>';
  }
}

async function addStudent(){
  const s = {
    firstName: document.getElementById('firstName').value,
    lastName: document.getElementById('lastName').value,
    email: document.getElementById('email').value,
    rollNo: document.getElementById('rollNo').value,
    className: document.getElementById('className').value
  };
  try{
    const res = await fetch(API, {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(s)
    });
    if(res.ok){ fetchList(); clearForm(); }
    else alert('Failed to add');
  }catch(e){
    alert('Backend not running. This demo UI will work when backend is available.');
  }
}

function clearForm(){
  ['firstName','lastName','email','rollNo','className'].forEach(id => document.getElementById(id).value='');
}

async function del(id){
  if(!confirm('Delete this student?')) return;
  await fetch(`${API}/${id}`, { method: 'DELETE' });
  fetchList();
}

// initial
fetchList();
