import { NavLink } from 'react-router-dom';

export default function Sidebar({ items }) {
  return (
    <aside className="sidebar">
      {items.map((item) => (
        <NavLink key={item.to} to={item.to} end={item.end} className={({isActive}) => isActive ? 'active' : ''}>
          {item.label}
        </NavLink>
      ))}
    </aside>
  );
}
